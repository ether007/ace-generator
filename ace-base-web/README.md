npm config set registry https://registry.npm.taobao.org
// 配置后可通过下面方式来验证是否成功
npm config get registry
npm install --global vue-cli

vue init webpack web
cd web
npm install
npm run dev