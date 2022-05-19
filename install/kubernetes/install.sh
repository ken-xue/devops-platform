#!/usr/bin/env sh
########################### system config ###########################

# 关闭防火墙
systemctl disable firewalld
systemctl stop firewalld
# 关闭selinux
# 临时禁用selinux
setenforce 0
# 永久关闭 修改/etc/sysconfig/selinux文件设置
sed -i 's/SELINUX=permissive/SELINUX=disabled/' /etc/sysconfig/selinux
sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config
# 禁用交换分区
swapoff -a
# 永久禁用，打开/etc/fstab注释掉swap那一行。
sed -i 's/.*swap.*/#&/' /etc/fstab
# 修改内核参数
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system

########################### kubernetes install script ###########################

# 执行配置k8s阿里云源
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
# 安装kubeadm、kubectl、kubelet
yum install -y kubectl-1.16.0-0 kubeadm-1.16.0-0 kubelet-1.16.0-0
# 启动kubelet服务
systemctl enable kubelet && systemctl start kubelet

########################### download image ###########################

#kubernetes版本
export KUBERNETES_VERSION=v1.21.1
#镜像下载地址
export IMAGE_ADDRESS=registry.cn-hangzhou.aliyuncs.com

#查看所需镜像
kubeadm config images list
#将上面的镜像版本复制下来
images=(
    kube-apiserver:$KUBERNETES_VERSION
    kube-controller-manager:$KUBERNETES_VERSION
    kube-scheduler:$KUBERNETES_VERSION
    kube-proxy:$KUBERNETES_VERSION
    pause:3.4.1
    etcd:3.4.13-0
    coredns/coredns:v1.8.0
)
#迭代下载
for imageName in ${images[@]} ; do
    docker pull $IMAGE_ADDRESS/google_containers/$imageName
    docker tag $IMAGE_ADDRESS/google_containers/$imageName k8s.gcr.io/$imageName
    docker rmi $IMAGE_ADDRESS/google_containers/$imageName
done

########################### deploy cicd-platform install script ###########################

kubeadm init

export KUBECONFIG=/etc/kubernetes/admin.conf

########################### deploy cicd-platform install script ###########################


