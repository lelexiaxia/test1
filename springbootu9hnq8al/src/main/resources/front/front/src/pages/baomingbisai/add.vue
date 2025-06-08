<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="比赛名称" prop="bisaimingcheng">
				<el-input v-model="ruleForm.bisaimingcheng" 
					placeholder="比赛名称" clearable :disabled=" false  ||ro.bisaimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="比赛类型" prop="bisaileixing">
				<el-input v-model="ruleForm.bisaileixing" 
					placeholder="比赛类型" clearable :disabled=" false  ||ro.bisaileixing"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="比赛图片" v-if="type!='cross' || (type=='cross' && !ro.bisaitupian)" prop="bisaitupian">
				<file-upload
					tip="点击上传比赛图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.bisaitupian?ruleForm.bisaitupian:''"
					@change="bisaitupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="比赛图片" prop="bisaitupian">
				<img v-if="ruleForm.bisaitupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.bisaitupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.bisaitupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="比赛地点" prop="bisaididian">
				<el-input v-model="ruleForm.bisaididian" 
					placeholder="比赛地点" clearable :disabled=" false  ||ro.bisaididian"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="报名时间" prop="baomingshijian">
				<el-date-picker
					:disabled=" false  ||ro.baomingshijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.baomingshijian" 
					type="date"
					placeholder="报名时间">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="玩家账号" prop="wanjiazhanghao">
				<el-input v-model="ruleForm.wanjiazhanghao" 
					placeholder="玩家账号" clearable :disabled=" false  ||ro.wanjiazhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="玩家姓名" prop="wanjiaxingming">
				<el-input v-model="ruleForm.wanjiaxingming" 
					placeholder="玩家姓名" clearable :disabled=" false  ||ro.wanjiaxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="报名备注" prop="baomingbeizhu">
				<editor 
					v-model="ruleForm.baomingbeizhu" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>

			<!-- 添加支付组件 -->
			<el-form-item class="add-item" label="报名费用" prop="baomingfei">
				<el-input-number 
					v-model="ruleForm.baomingfei" 
					:min="0" 
					:precision="2" 
					:step="10"
					placeholder="请输入报名费用">
				</el-input-number>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn" type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">提交报名</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>

		<!-- 支付弹窗 -->
		<el-dialog
			title="支付报名费用"
			:visible.sync="payDialogVisible"
			width="500px"
			:close-on-click-modal="false"
			:close-on-press-escape="false"
			:show-close="false">
			<simple-pay 
				:amount="ruleForm.baomingfei" 
				:remark="'比赛报名费：' + ruleForm.bisaimingcheng"
				@payment-success="handlePaymentSuccess"
				@payment-cancel="handlePaymentCancel">
			</simple-pay>
		</el-dialog>
	</div>
</template>

<script>
import SimplePay from '@/vue/simple-pay.vue'

export default {
	components: {
		SimplePay
	},
	data() {
		return {
			id: '',
			baseUrl: '',
			ro:{
				bisaimingcheng : false,
				bisaileixing : false,
				bisaitupian : false,
				bisaididian : false,
				baomingshijian : false,
				wanjiazhanghao : false,
				wanjiaxingming : false,
				baomingbeizhu : false,
				crossuserid : false,
				crossrefid : false,
			},
			type: '',
			userTableName: localStorage.getItem('UserTableName'),
			ruleForm: {
				bisaimingcheng: '',
				bisaileixing: '',
				bisaitupian: '',
				bisaididian: '',
				baomingshijian: '',
				wanjiazhanghao: '',
				wanjiaxingming: '',
				baomingbeizhu: '',
				crossuserid: '',
				crossrefid: '',
				baomingfei: 0,
			},


			rules: {
				bisaimingcheng: [
				],
				bisaileixing: [
				],
				bisaitupian: [
				],
				bisaididian: [
				],
				baomingshijian: [
				],
				wanjiazhanghao: [
				],
				wanjiaxingming: [
				],
				baomingbeizhu: [
				],
				crossuserid: [
				],
				crossrefid: [
				],
			},
			centerType: false,
			payDialogVisible: false,
		};
	},
	computed: {



	},
	created() {
		if(this.$route.query.centerType){
			this.centerType = true
		}
		//this.bg();
		let type = this.$route.query.type ? this.$route.query.type : '';
		this.init(type);
		this.baseUrl = this.$config.baseUrl;
		this.ruleForm.baomingshijian = this.getCurDate()
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
					if(o=='bisaimingcheng'){
						this.ruleForm.bisaimingcheng = obj[o];
						this.ro.bisaimingcheng = true;
						continue;
					}
					if(o=='bisaileixing'){
						this.ruleForm.bisaileixing = obj[o];
						this.ro.bisaileixing = true;
						continue;
					}
					if(o=='bisaitupian'){
						this.ruleForm.bisaitupian = obj[o].split(",")[0];
						this.ro.bisaitupian = true;
						continue;
					}
					if(o=='bisaididian'){
						this.ruleForm.bisaididian = obj[o];
						this.ro.bisaididian = true;
						continue;
					}
					if(o=='baomingshijian'){
						this.ruleForm.baomingshijian = obj[o];
						this.ro.baomingshijian = true;
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
					if(o=='baomingbeizhu'){
						this.ruleForm.baomingbeizhu = obj[o];
						this.ro.baomingbeizhu = true;
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
			}else if(type=='edit'){
				this.info()
			}
			// 获取用户信息
			this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
				if (res.data.code == 0) {
					var json = res.data.data;
					if((json.wanjiazhanghao!=''&&json.wanjiazhanghao) || json.wanjiazhanghao==0){
						this.ruleForm.wanjiazhanghao = json.wanjiazhanghao;
						this.ro.wanjiazhanghao = true;
					}
					if((json.wanjiaxingming!=''&&json.wanjiaxingming) || json.wanjiaxingming==0){
						this.ruleForm.wanjiaxingming = json.wanjiaxingming;
						this.ro.wanjiaxingming = true;
					}
				}
			});

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
			this.$http.get(`baomingbisai/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
				if (res.data.code == 0) {
					this.ruleForm = res.data.data;
				}
			});
		},
		// 提交
		async onSubmit() {
			this.$refs.ruleForm.validate(valid => {
				if (valid) {
					if (this.ruleForm.baomingfei > 0) {
						// 如果有报名费用，显示支付弹窗
						this.payDialogVisible = true
					} else {
						// 如果没有报名费用，直接提交
						this.submitForm()
					}
				}
			})
		},
		submitForm() {
			this.$http.post(`baomingbisai/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		// 返回
		back() {
			this.$router.go(-1);
		},
		bisaitupianUploadChange(fileUrls) {
			this.ruleForm.bisaitupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		handlePaymentSuccess() {
			this.payDialogVisible = false
			this.submitForm()
		},
		handlePaymentCancel() {
			this.payDialogVisible = false
		}
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
	.el-input-number {
		width: 200px;
	}
</style>
