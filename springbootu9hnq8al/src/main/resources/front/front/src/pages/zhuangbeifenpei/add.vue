<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="装备编号" prop="zhuangbeibianhao">
				<el-input v-model="ruleForm.zhuangbeibianhao" placeholder="装备编号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="装备名称" prop="zhuangbeimingcheng">
				<el-input v-model="ruleForm.zhuangbeimingcheng" 
					placeholder="装备名称" clearable :disabled=" false  ||ro.zhuangbeimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="装备分类" prop="zhuangbeifenlei">
				<el-input v-model="ruleForm.zhuangbeifenlei" 
					placeholder="装备分类" clearable :disabled=" false  ||ro.zhuangbeifenlei"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="装备照片" v-if="type!='cross' || (type=='cross' && !ro.zhuangbeizhaopian)" prop="zhuangbeizhaopian">
				<file-upload
					tip="点击上传装备照片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.zhuangbeizhaopian?ruleForm.zhuangbeizhaopian:''"
					@change="zhuangbeizhaopianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="装备照片" prop="zhuangbeizhaopian">
				<img v-if="ruleForm.zhuangbeizhaopian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.zhuangbeizhaopian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.zhuangbeizhaopian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="分配数量" prop="zhuangbeishuliang">
				<el-input v-model.number="ruleForm.zhuangbeishuliang" 
					placeholder="分配数量" clearable :disabled=" false  ||ro.zhuangbeishuliang"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="分配时间" prop="fenpeishijian">
				<el-date-picker
					:disabled=" false  ||ro.fenpeishijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.fenpeishijian" 
					type="date"
					placeholder="分配时间">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="玩家账号" prop="wanjiazhanghao">
				<el-select  @change="wanjiazhanghaoChange" v-model="ruleForm.wanjiazhanghao" placeholder="请选择玩家账号">
					<el-option
						v-for="(item,index) in wanjiazhanghaoOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="玩家姓名" prop="wanjiaxingming">
				<el-input v-model="ruleForm.wanjiaxingming" 
					placeholder="玩家姓名" clearable :disabled=" false  ||ro.wanjiaxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="分配备注" prop="fenpeibeizhu">
				<editor 
					v-model="ruleForm.fenpeibeizhu" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">更新信息</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
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
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
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
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
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
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.fenpeishijian = this.getCurDate()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
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
							this.ruleForm.zhuangbeizhaopian = obj[o].split(",")[0];
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
					this.ruleForm.zhuangbeishuliang = 0;
					this.ro.zhuangbeishuliang = false;
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
					}
				});
				this.$http.get('option/wanjia/wanjiazhanghao', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.wanjiazhanghaoOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},
			// 下二随
			wanjiazhanghaoChange () {
				this.$http.get('follow/wanjia/wanjiazhanghao?columnValue=' + this.ruleForm.wanjiazhanghao, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						if(res.data.data.wanjiaxingming){
							this.ruleForm.wanjiaxingming = res.data.data.wanjiaxingming
						}
					}
				});
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`zhuangbeifenpei/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.zhuangbeibianhao){
					this.ruleForm.zhuangbeibianhao = String(this.ruleForm.zhuangbeibianhao)
				}
				if(!this.ruleForm.zhuangbeishuliang){
					this.$message.error("分配数量不能为空");
					return
				}
				var obj = JSON.parse(localStorage.getItem('crossObj'));
				var table = localStorage.getItem('crossTable');
				obj.zhuangbeishuliang = obj.zhuangbeishuliang - this.ruleForm.zhuangbeishuliang
				if(obj.zhuangbeishuliang<0){
					this.$message.error("分配数量不足");
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				var finishNum = 0;
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
								else {
									crossuserid=Number(localStorage.getItem('frontUserid'));
									crossrefid=obj['id'];
									crossoptnum=localStorage.getItem('statusColumnName');
									crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
								}
							}
							if(crossrefid && crossuserid) {
								this.ruleForm.crossuserid=crossuserid;
								this.ruleForm.crossrefid=crossrefid;
								var params = {
									page: 1,
									limit: 10,
									crossuserid:crossuserid,
									crossrefid:crossrefid,
								}
								await this.$http.get('zhuangbeifenpei/list', {
									params: params
								}).then(res => {
									finishNum = res.data.data.total
								});
							}
							if(finishNum>=crossoptnum) {
								this.$message({
									message: localStorage.getItem('tips'),
									type: 'error',
									duration: 1500,
								});
								return false;
							}
						}
						// 跨表计算
						var obj = JSON.parse(localStorage.getItem('crossObj'));
						var table = localStorage.getItem('crossTable');

						obj.zhuangbeishuliang = Number((parseFloat(obj.zhuangbeishuliang) - parseFloat(this.ruleForm.zhuangbeishuliang)).toFixed(2))

						await this.$http.post(table+`/update`,obj).then(res => {});
						await this.$http.post(`zhuangbeifenpei/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
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
				this.$router.go(-1);
			},
			zhuangbeizhaopianUploadChange(fileUrls) {
				this.ruleForm.zhuangbeizhaopian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 0 20px;
		margin: 0px auto;
		color: #666;
		background: #fff;
		width: 1400px;
		font-size: 16px;
		position: relative;
		.add-update-form {
			margin: 20px 0 0;
			display: flex;
			width: 100%;
			border-color: #1A3194;
			border-width: 1px 0 0 1px;
			position: relative;
			border-style: solid;
			flex-wrap: wrap;
			.add-item.el-form-item {
				padding: 0;
				margin: 0;
				background: none;
				width: 100%;
				border-color: #1A3194;
				border-width: 0 1px 1px 0;
				border-style: solid;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					width: 180px;
					font-size: inherit;
					line-height: 60px;
					text-align: center;
				}
				/deep/ .el-form-item__content {
					margin-left: 180px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 0px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 60px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 60px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 0px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 60px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 60px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
					border: 0px solid #ddd;
					border-radius: 0px;
					padding: 0 10px;
					color: inherit;
					width: 100%;
					font-size: 16px;
					min-width: inherit !important;
					height: 60px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 60px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 0px solid #ddd;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					width: 100%;
					font-size: 16px;
					height: 60px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					height: 60px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload__tip {
					color: #888;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 0px solid #ddd;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 100%;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0px solid #ddd;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 100%;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: inherit;
					font-size: inherit;
				}
				/deep/ textarea::placeholder {
					color: inherit;
					font-size: inherit;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					min-height: 350px;
					border-color: #ccc;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					object-fit: cover;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0px;
					padding: 0 20px;
					margin: 0;
					color: #333;
					background: #03abe930;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 34px;
					height: 34px;
				}
				.viewBtn:hover {
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #333;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 0px;
					outline: none;
					background: #ddd;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
					background: #eee;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px 0;
				width: 100%;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 2px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					background: #1A3194;
					display: inline-block;
					width: auto;
					font-size: 16px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.submitBtn:hover {
					opacity: 0.8;
					.icon {
					}
					.text {
					}
				}
				.closeBtn {
					border: 0px solid #ddd;
					cursor: pointer;
					padding: 0 15px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 2px;
					background: #03cce9;
					width: auto;
					text-align: center;
					min-width: 110px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn:hover {
					opacity: 0.8;
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
