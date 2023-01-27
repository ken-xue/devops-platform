package pipeline

import (
	"github.com/goccy/go-json"
	"testing"
)

func TestParserPipeline(t *testing.T) {
	s := `{"edges":["source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8&&target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8","source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8&&target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8","source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8&&target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8","source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8&&target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8","source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8&&target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8","source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8&&target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8","source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8&&target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8","source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8&&target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8","source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8&&target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8","source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8&&target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8","source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8&&target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8","source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8&&target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8","source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8&&target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8","source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8&&target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8","source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8&&target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8","source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8&&target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8","source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8&&target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8","source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8&&target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8","source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8&&target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8","source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8&&target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8","source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8&&target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8","source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8&&target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8","source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8&&target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8","source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8&&target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8","source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8&&target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8","source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8&&target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8","source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8&&target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8","source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8&&target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8","source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8&&target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8","source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8&&target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8","source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8&&target-ff189401-96c4-11ec-92a6-c7acdf05a7f8","source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8&&target-ff189401-96c4-11ec-92a6-c7acdf05a7f8","source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8&&target-ff189401-96c4-11ec-92a6-c7acdf05a7f8","source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8&&target-ff189401-96c4-11ec-92a6-c7acdf05a7f8","source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8&&target-ff189401-96c4-11ec-92a6-c7acdf05a7f8","source-ff189402-96c4-11ec-92a6-c7acdf05a7f8&&target-05da5080-96c5-11ec-92a6-c7acdf05a7f8","source-ff189402-96c4-11ec-92a6-c7acdf05a7f8&&target-05da5080-96c5-11ec-92a6-c7acdf05a7f8","source-ff189402-96c4-11ec-92a6-c7acdf05a7f8&&target-05da5080-96c5-11ec-92a6-c7acdf05a7f8","source-ff189402-96c4-11ec-92a6-c7acdf05a7f8&&target-05da5080-96c5-11ec-92a6-c7acdf05a7f8","source-ff189402-96c4-11ec-92a6-c7acdf05a7f8&&target-05da5080-96c5-11ec-92a6-c7acdf05a7f8","source-05da5081-96c5-11ec-92a6-c7acdf05a7f8&&target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8","source-05da5081-96c5-11ec-92a6-c7acdf05a7f8&&target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8","source-05da5081-96c5-11ec-92a6-c7acdf05a7f8&&target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8","source-05da5081-96c5-11ec-92a6-c7acdf05a7f8&&target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8","source-05da5081-96c5-11ec-92a6-c7acdf05a7f8&&target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8","source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8&&target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8","source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8&&target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8","source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8&&target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8","source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8&&target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8","source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8&&target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8","source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8&&target-152ae861-96c5-11ec-92a6-c7acdf05a7f8","source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8&&target-152ae861-96c5-11ec-92a6-c7acdf05a7f8","source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8&&target-152ae861-96c5-11ec-92a6-c7acdf05a7f8","source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8&&target-152ae861-96c5-11ec-92a6-c7acdf05a7f8","source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8&&target-152ae861-96c5-11ec-92a6-c7acdf05a7f8","source-5fbc1802-9ac5-11ec-924a-37594f3c7984&&target-6c3489a1-9ac5-11ec-924a-37594f3c7984"],"endpoints":[{"data":{"value":"输入"},"id":"target-d4e86841-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-ff189401-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-ff189402-96c4-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-05da5080-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-05da5081-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-152ae861-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输出"},"id":"source-152ae862-96c5-11ec-92a6-c7acdf05a7f8"},{"data":{"value":"输入"},"id":"target-5fbc1801-9ac5-11ec-924a-37594f3c7984"},{"data":{"value":"输出"},"id":"source-5fbc1802-9ac5-11ec-924a-37594f3c7984"},{"data":{"value":"输入"},"id":"target-6c3489a1-9ac5-11ec-924a-37594f3c7984"},{"data":{"value":"输出"},"id":"source-6c3489a2-9ac5-11ec-924a-37594f3c7984"}],"head":"aaa","nodes":[{"data":{"value":"Java代码扫描"},"id":"node-d4e86840-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-d4e86842-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-d4e86841-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":20,"top":91}},{"data":{"value":"Java安全扫描"},"id":"node-d84a7f00-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-d84a7f02-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-d84a7f01-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":112,"top":273}},{"data":{"value":"Maven 单元测试"},"id":"node-dbd9e750-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-dbd9e752-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-dbd9e751-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":27,"top":450}},{"data":{"value":"Cobertura覆盖率采集"},"id":"node-e3d3aea0-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-e3d3aea2-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-e3d3aea1-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":62,"top":629}},{"data":{"value":"Java 构建"},"id":"node-ebde3ed0-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-ebde65e1-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-ebde65e0-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":406,"top":663}},{"data":{"value":"Java 构建测试上传"},"id":"node-f13595e0-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-f135bcf0-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-f13595e1-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":702,"top":729}},{"data":{"value":"Java 镜像构建"},"id":"node-f84aa280-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-f84aa282-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-f84aa281-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":532,"top":895}},{"data":{"value":"Docker部署"},"id":"node-ff189400-96c4-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-ff189402-96c4-11ec-92a6-c7acdf05a7f8"],"targets":["target-ff189401-96c4-11ec-92a6-c7acdf05a7f8"]},"position":{"left":232,"top":965}},{"data":{"value":"人工卡点"},"id":"node-05da2970-96c5-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-05da5081-96c5-11ec-92a6-c7acdf05a7f8"],"targets":["target-05da5080-96c5-11ec-92a6-c7acdf05a7f8"]},"position":{"left":-1,"top":1139}},{"data":{"value":"Jenkins 任务"},"id":"node-0a7de7f0-96c5-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-0a7de7f2-96c5-11ec-92a6-c7acdf05a7f8"],"targets":["target-0a7de7f1-96c5-11ec-92a6-c7acdf05a7f8"]},"position":{"left":350,"top":1195}},{"data":{"value":"合并代码"},"id":"node-1064d4d0-96c5-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-1064d4d2-96c5-11ec-92a6-c7acdf05a7f8"],"targets":["target-1064d4d1-96c5-11ec-92a6-c7acdf05a7f8"]},"position":{"left":698,"top":1229}},{"data":{"value":"执行命令"},"id":"node-152ae860-96c5-11ec-92a6-c7acdf05a7f8","points":{"sources":["source-152ae862-96c5-11ec-92a6-c7acdf05a7f8"],"targets":["target-152ae861-96c5-11ec-92a6-c7acdf05a7f8"]},"position":{"left":408,"top":1331}},{"data":{"icon":"el-icon-magic-stick","value":"PHP Metrics 静态扫描"},"id":"node-5fbc1800-9ac5-11ec-924a-37594f3c7984","points":{"sources":["source-5fbc1802-9ac5-11ec-924a-37594f3c7984"],"targets":["target-5fbc1801-9ac5-11ec-924a-37594f3c7984"]},"position":{"left":395,"top":195}},{"data":{"icon":"el-icon-s-tools","value":"ACK Nginx Ingress 灰度发布"},"id":"node-6c3489a0-9ac5-11ec-924a-37594f3c7984","points":{"sources":["source-6c3489a2-9ac5-11ec-924a-37594f3c7984"],"targets":["target-6c3489a1-9ac5-11ec-924a-37594f3c7984"]},"position":{"left":385,"top":363}}]}`
	graph := Graph{}
	json.Unmarshal([]byte(s), &graph)
	t.Logf("graph = %v", graph)
}