wget https://dl.min.io/server/minio/release/linux-amd64/minio
chmod +x minio
MINIO_ROOT_USER=admin MINIO_ROOT_PASSWORD=password ./minio server /mnt/data --console-address ":9001"


#!/bin/bash
export MINIO_ACCESS_KEY=admin
export MINIO_SECRET_KEY=admin@minio
/home/minio/app/minio server --config-dir /home/minio/config  --console-address ":9001" \
http://10.98.66.62:9000/home/minio/data \
http://10.98.66.63:9000/home/minio/data \
http://10.98.66.64:9000/home/minio/data \
http://10.98.66.65:9000/home/minio/data