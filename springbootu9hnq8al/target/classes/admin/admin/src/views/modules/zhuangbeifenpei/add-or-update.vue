<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'" label="装备编号" prop="zhuangbeibianhao" >
					<el-input v-model="ruleForm.zhuangbeibianhao" placeholder="装备编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.zhuangbeibianhao" label="装备编号" prop="zhuangbeibianhao" >
					<el-input v-model="ruleForm.zhuangbeibianhao" placeholder="装备编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="装备名称" prop="zhuangbeimingcheng" >
					<el-input v-model="ruleForm.zhuangbeimingcheng" placeholder="装备名称" clearable  :readonly="ro.zhuangbeimingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="装备名称" prop="zhuangbeimingcheng" >
					<el-input v-model="ruleForm.zhuangbeimingcheng" placeholder="装备名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="装备分类" prop="zhuangbeifenlei" >
					<el-input v-model="ruleForm.zhuangbeifenlei" placeholder="装备分类" clearable  :readonly="ro.zhuangbeifenlei"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="装备分类" prop="zhuangbeifenlei" >
					<el-input v-model="ruleForm.zhuangbeifenlei" placeholder="装备分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.zhuangbeizhaopian" label="装备照片" prop="zhuangbeizhaopian" >
					<file-upload
						tip="点击上传装备照片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.zhuangbeizhaopian?ruleForm.zhuangbeizhaopian:''"
						@change="zhuangbeizhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.zhuangbeizhaopian" label="装备照片" prop="zhuangbeizhaopian" >
					<img v-if="ruleForm.zhuangbeizhaopian.substring(0,4)=='http'&&ruleForm.zhuangbeizhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.zhuangbeizhaopian" width="100" height="100">
					<img v-else-if="ruleForm.zhuangbeizhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.zhuangbeizhaopian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.zhuangbeizhaopian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="分配数量" prop="zhuangbeishuliang" >
					<el-input v-model.number="ruleForm.zhuangbeishuliang" placeholder="分配数量" clearable  :readonly="ro.zhuangbeishuliang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="分配数量" prop="zhuangbeishuliang" >
					<el-input v-model="ruleForm.zhuangbeishuliang" placeholder="分配数量" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="分配时间" prop="fenpeishijian" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.fenpeishijian" 
						type="date"
						:readonly="ro.fenpeishijian"
						placeholder="分配时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.fenpeishijian" label="分配时间" prop="fenpeishijian" >
					<el-input v-model="ruleForm.fenpeishijian" placeholder="分配时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'" label="玩家账号" prop="wanjiazhanghao" >
					<el-select :disabled="ro.wanjiazhanghao" @change="wanjiazhanghaoChange" v-model="ruleForm.wanjiazhanghao" placeholder="请选择玩家账号">
						<el-option
							v-for="(item,index) in wanjiazhanghaoOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.wanjiazhanghao" label="玩家账号" prop="wanjiazhanghao" >
					<el-input v-model="ruleForm.wanjiazhanghao" placeholder="玩家账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="玩家姓名" prop="wanjiaxingming" >
					<el-input v-model="ruleForm.wanjiaxingming" placeholder="玩家姓名" clearable  :readonly="ro.wanjiaxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="玩家姓名" prop="wanjiaxingming" >
					<el-input v-model="ruleForm.wanjiaxingming" placeholder="玩家姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="分配备注" prop="fenpeibeizhu" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.fenpeibeizhu" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.fenpeibeizhu" label="分配备注" prop="fenpeibeizhu" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.fenpeibeizhu"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					zhuangbeibianhao : false,
					zhuangbeimingcheng : false,
					zhuangbeifenlei : false,
					zhuangbeizhaopian : false,
					zhuangbeishuliang : false,
					fenpeishijian : false,
					fenpeibeizhu : false,
					wanjiazhanghao : false,
					wanjiaxingming : false,
					crossuserid : false,
					crossrefid : false,
				},
			
				ruleForm: {
					zhuangbeibianhao: this.getUUID(),
					zhuangbeimingcheng: '',
					zhuangbeifenlei: '',
					zhuangbeizhaopian: '',
					zhuangbeishuliang: '',
					fenpeishijian: '',
					fenpeibeizhu: '',
					wanjiazhanghao: '',
					wanjiaxingming: '',
					crossuserid: '',
					crossrefid: '',
				},
				wanjiazhanghaoOptions: [],

				rules: {
					zhuangbeibianhao: [
					],
					zhuangbeimingcheng: [
					],
					zhuangbeifenlei: [
					],
					zhuangbeizhaopian: [
					],
					zhuangbeishuliang: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					fenpeishijian: [
					],
					fenpeibeizhu: [
					],
					wanjiazhanghao: [
					],
					wanjiaxingming: [
					],
					crossuserid: [
					],
					crossrefid: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.fenpeishijian = this.getCurDate()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='zhuangbeibianhao'){
							this.ruleForm.zhuangbeibianhao = obj[o];
							this.ro.zhuangbeibianhao = true;
							continue;
						}
						if(o=='zhuangbeimingcheng'){
							this.ruleForm.zhuangbeimingcheng = obj[o];
							this.ro.zhuangbeimingcheng = true;
							continue;
						}
						if(o=='zhuangbeifenlei'){
							this.ruleForm.zhuangbeifenlei = obj[o];
							this.ro.zhuangbeifenlei = true;
							continue;
						}
						if(o=='zhuangbeizhaopian'){
							this.ruleForm.zhuangbeizhaopian = obj[o];
							this.ro.zhuangbeizhaopian = true;
							continue;
						}
						if(o=='zhuangbeishuliang'){
							this.ruleForm.zhuangbeishuliang = obj[o];
							this.ro.zhuangbeishuliang = true;
							continue;
						}
						if(o=='fenpeishijian'){
							this.ruleForm.fenpeishijian = obj[o];
							this.ro.fenpeishijian = true;
							continue;
						}
						if(o=='fenpeibeizhu'){
							this.ruleForm.fenpeibeizhu = obj[o];
							this.ro.fenpeibeizhu = true;
							continue;
						}
						if(o=='wanjiazhanghao'){
							this.ruleForm.wanjiazhanghao = obj[o];
							this.ro.wanjiazhanghao = true;
							continue;
						}
						if(o=='wanjiaxingming'){
							this.ruleForm.wanjiaxingming = obj[o];
							this.ro.wanjiaxingming = true;
							continue;
						}
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
							continue;
						}
					}
					this.ruleForm.zhuangbeishuliang = 0
					this.ro.zhuangbeishuliang = false;
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/wanjia/wanjiazhanghao`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.wanjiazhanghaoOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 下二随
			wanjiazhanghaoChange () {
				this.$http({
					url: `follow/wanjia/wanjiazhanghao?columnValue=`+ this.ruleForm.wanjiazhanghao,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(data.data.wanjiaxingming){
							this.ruleForm.wanjiaxingming = data.data.wanjiaxingming
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `zhuangbeifenpei/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.fenpeibeizhu = this.ruleForm.fenpeibeizhu.replace(reg,'../../../springbootu9hnq8al/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.zhuangbeibianhao) {
						this.ruleForm.zhuangbeibianhao = String(this.ruleForm.zhuangbeibianhao)
					}
					if(this.ruleForm.zhuangbeizhaopian!=null) {
						this.ruleForm.zhuangbeizhaopian = this.ruleForm.zhuangbeizhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.zhuangbeishuliang){
							this.$message.error("分配数量不能为空");
							return
						}
						objcross.zhuangbeishuliang = objcross.zhuangbeishuliang - this.ruleForm.zhuangbeishuliang
						if(objcross.zhuangbeishuliang<0){
							this.$message.error("分配数量不足");
							return
						}
											}
					//更新跨表属性
					var crossuserid;
					var crossrefid;
					var crossoptnum;
					var finishNum = 0;
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: objcross
										}).then(({ data }) => {});
									}
									else {
										crossuserid=this.$storage.get('userid');
										crossrefid=obj['id'];
										crossoptnum=this.$storage.get('statusColumnName');
										crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
									}
								}
								if(crossrefid && crossuserid) {
									this.ruleForm.crossuserid = crossuserid;
									this.ruleForm.crossrefid = crossrefid;
									let params = { 
										page: 1, 
										limit: 10, 
										crossuserid:this.ruleForm.crossuserid,
										crossrefid:this.ruleForm.crossrefid,
									} 
									await this.$http({ 
										url: "zhuangbeifenpei/page", 
										method: "get", 
										params: params 
									}).then(({ 
										data 
									}) => { 
										if (data && data.code === 0) {
											finishNum = data.data.total
										}
									})
								}
								if(finishNum>=crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								}
							}
							
							await this.$http({
								url: `zhuangbeifenpei/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.zhuangbeifenpeiCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
									var table = this.$storage.get('crossTable');
									await this.$http({
										url: `${table}/update`,
										method: "post",
										data: objcross
									}).then(({ data }) => {});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.zhuangbeifenpeiCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			zhuangbeizhaopianUploadChange(fileUrls) {
				this.ruleForm.zhuangbeizhaopian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
		background: none;
		width: 100%;
	}
	.add-update-preview {
		border-radius: 10px;
		padding: 40px 25% 40px 15%;
		background: #ffffff;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 100%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		white-space: nowrap;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		min-height: 150px;
		height: auto;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #56cee3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #03b6be;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #ebc78a;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #56cee3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 6px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #8faedc;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
