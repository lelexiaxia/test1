<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="场地名称" prop="changdimingcheng">
				<el-input v-model="ruleForm.changdimingcheng" 
					placeholder="场地名称" clearable :disabled=" false  ||ro.changdimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="场地类型" prop="changdileixing">
				<el-select v-model="ruleForm.changdileixing" placeholder="请选择场地类型" :disabled=" false  ||ro.changdileixing" >
					<el-option
						v-for="(item,index) in changdileixingOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="场地图片" v-if="type!='cross' || (type=='cross' && !ro.changditupian)" prop="changditupian">
				<file-upload
					tip="点击上传场地图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.changditupian?ruleForm.changditupian:''"
					@change="changditupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="场地图片" prop="changditupian">
				<img v-if="ruleForm.changditupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.changditupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.changditupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="场地规模" prop="changdiguimo">
				<el-input v-model="ruleForm.changdiguimo" 
					placeholder="场地规模" clearable :disabled=" false  ||ro.changdiguimo"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="场地位置" prop="changdiweizhi">
				<el-input v-model="ruleForm.changdiweizhi" 
					placeholder="场地位置" clearable :disabled=" false  ||ro.changdiweizhi"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="容纳人数" prop="rongnarenshu">
				<el-input v-model.number="ruleForm.rongnarenshu" 
					placeholder="容纳人数" clearable :disabled=" false  ||ro.rongnarenshu"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="开放时间" prop="kaifangshijian">
				<el-date-picker
					:disabled=" false  ||ro.kaifangshijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.kaifangshijian" 
					type="date"
					placeholder="开放时间">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item"  label="场地状态" prop="changdizhuangtai">
				<el-select v-model="ruleForm.changdizhuangtai" placeholder="请选择场地状态" :disabled="true  ||ro.changdizhuangtai" >
					<el-option
						v-for="(item,index) in changdizhuangtaiOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="场地设施" prop="changdisheshi">
				<el-input
					type="textarea"
					:rows="8"
					placeholder="场地设施"
					v-model="ruleForm.changdisheshi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="场地详情" prop="changdixiangqing">
				<editor 
					v-model="ruleForm.changdixiangqing" 
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
					changdimingcheng : false,
					changdileixing : false,
					changditupian : false,
					changdiguimo : false,
					changdiweizhi : false,
					rongnarenshu : false,
					kaifangshijian : false,
					changdizhuangtai : false,
					changdisheshi : false,
					changdixiangqing : false,
					clicktime : false,
					clicknum : false,
					discussnum : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					changdimingcheng: '',
					changdileixing: '',
					changditupian: '',
					changdiguimo: '',
					changdiweizhi: '',
					rongnarenshu: '',
					kaifangshijian: '',
					changdizhuangtai: '未预约' ,
					changdisheshi: '',
					changdixiangqing: '',
					clicktime: '',
					clicknum: '',
					discussnum: '',
					storeupnum: '',
				},
				changdileixingOptions: [],
				changdizhuangtaiOptions: [],


				rules: {
					changdimingcheng: [
					],
					changdileixing: [
					],
					changditupian: [
					],
					changdiguimo: [
					],
					changdiweizhi: [
					],
					rongnarenshu: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					kaifangshijian: [
					],
					changdizhuangtai: [
					],
					changdisheshi: [
					],
					changdixiangqing: [
					],
					clicktime: [
					],
					clicknum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
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
			this.ruleForm.kaifangshijian = this.getCurDate()
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
						if(o=='changdimingcheng'){
							this.ruleForm.changdimingcheng = obj[o];
							this.ro.changdimingcheng = true;
							continue;
						}
						if(o=='changdileixing'){
							this.ruleForm.changdileixing = obj[o];
							this.ro.changdileixing = true;
							continue;
						}
						if(o=='changditupian'){
							this.ruleForm.changditupian = obj[o].split(",")[0];
							this.ro.changditupian = true;
							continue;
						}
						if(o=='changdiguimo'){
							this.ruleForm.changdiguimo = obj[o];
							this.ro.changdiguimo = true;
							continue;
						}
						if(o=='changdiweizhi'){
							this.ruleForm.changdiweizhi = obj[o];
							this.ro.changdiweizhi = true;
							continue;
						}
						if(o=='rongnarenshu'){
							this.ruleForm.rongnarenshu = obj[o];
							this.ro.rongnarenshu = true;
							continue;
						}
						if(o=='kaifangshijian'){
							this.ruleForm.kaifangshijian = obj[o];
							this.ro.kaifangshijian = true;
							continue;
						}
						if(o=='changdizhuangtai'){
							this.ruleForm.changdizhuangtai = obj[o];
							this.ro.changdizhuangtai = true;
							continue;
						}
						if(o=='changdisheshi'){
							this.ruleForm.changdisheshi = obj[o];
							this.ro.changdisheshi = true;
							continue;
						}
						if(o=='changdixiangqing'){
							this.ruleForm.changdixiangqing = obj[o];
							this.ro.changdixiangqing = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
					}
				});
				this.$http.get('option/changdileixing/changdileixing', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.changdileixingOptions = res.data.data;
					}
				});
				this.changdizhuangtaiOptions = "已预约,未预约".split(',')

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`changdixinxi/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
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
							}
						}


						await this.$http.post(`changdixinxi/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
			changditupianUploadChange(fileUrls) {
				this.ruleForm.changditupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
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
