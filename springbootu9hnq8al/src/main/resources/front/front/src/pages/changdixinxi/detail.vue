<template>
	<div>
	<!--  -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'≡'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/changdixinxi?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.changdimingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang10"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="item">
						<div class="lable">场地类型</div>
						<div class="text "  >{{detail.changdileixing}}</div>
					</div>
					<div class="item">
						<div class="lable">场地规模</div>
						<div class="text "  >{{detail.changdiguimo}}</div>
					</div>
					<div class="item">
						<div class="lable">场地位置</div>
						<div class="text "  >{{detail.changdiweizhi}}</div>
					</div>
					<div class="item">
						<div class="lable">容纳人数</div>
						<div class="text "  >{{detail.rongnarenshu}}</div>
					</div>
					<div class="item">
						<div class="lable">开放时间</div>
						<div class="text "  >{{detail.kaifangshijian}}</div>
					</div>
					<div class="item">
						<div class="lable">场地状态</div>
						<div class="text "  >{{detail.changdizhuangtai}}</div>
					</div>
					<div class="item">
						<div class="lable">场地设施</div>
						<div class="text "  >{{detail.changdisheshi}}</div>
					</div>
					<div class="item">
						<div class="lable">点击次数</div>
						<div class="text "  >{{detail.clicknum}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('changdixinxi','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('changdixinxi','删除')" @click="delClick">删除</el-button>
						<el-button class="reserveBtn" 
							v-if="btnAuth('changdixinxi','预约')" 
							@click="onAcross('changdiyuyue','','','changdizhuangtai','已预约','已预约,未预约'.split(',')[0])" type="warning">
							预约
						</el-button>
					</div>
				</div>
			</div>
		
			<div class="detail-swpier2" v-if="detailBanner.length">
				<div class="swiper21">
					<div class="swiper-container mySwiper21">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
				<div class="swiper22">
					<div class="swiper-container mySwiper22">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
			</div>


		

			<div class="detail2">
				<div class="desc-view">
					<div class="desc-label">场地详情</div>
				</div>
				<div class="desc-text ql-snow ql-editor" v-html="detail.changdixiangqing"></div>
				<el-form class="add commentForm" :model="form" :rules="rules" ref="form">
					<el-form-item class="item" label="评论" prop="content">
						<editor
							v-model="form.content" 
							class="editor" 
							action="file/upload">
						</editor>
					</el-form-item>
					<el-form-item class="commentBtn">
						<el-button class="submitBtn" type="primary" @click="submitForm('form')">立即提交</el-button>
						<el-button class="resetBtn" @click="resetForm('form')">重置</el-button>
					</el-form-item>
				</el-form>
				
				<div v-if="infoList.length" class="comment-list">
					<div class="comment-item" v-for="item in infoList" :key="item.id" @mouseenter="discussEnter(item.id)" @mouseleave="discussLeave">
						<div class="istop" v-if="item.istop">
							<span class="icon iconfont icon-jiantou24"></span>
						</div>
						<div class="user">
							<el-image v-if="item.avatarurl" :size="50" :src="baseUrl + item.avatarurl"></el-image>
							<el-image v-if="!item.avatarurl" :size="50" :src="require('@/assets/touxiang.png')"></el-image>
							<div class="name">{{item.nickname}}</div>
						</div>
						<div class="comment-content-box">
							<div class="ql-snow ql-editor" v-html="item.content"></div>
							<div class="comment-time">{{item.addtime}}</div>
							<div class="zancai-box">
								<div v-if="!comcaiChange(item)" class="zan-item" :class="comzanChange(item)?'active':''" @click="comzanClick(item)">
									<span class="icon iconfont" :class="comzanChange(item)?'icon-zan11':'icon-zan07'"></span>
									<span class="label">{{comzanChange(item)?'已赞':'赞'}}</span>
									<span class="num">({{item.thumbsupnum}})</span>
								</div>
								<div v-if="!comzanChange(item)" class="cai-item" :class="comcaiChange(item)?'active':''" @click="comcaiClick(item)">
									<span class="icon iconfont" :class="comcaiChange(item)?'icon-cai16':'icon-cai01'"></span>
									<span class="label">{{comcaiChange(item)?'已踩':'踩'}}</span>
									<span class="num">({{item.crazilynum}})</span>
								</div>
							</div>
							<div class="comment-btn">
								<!-- <el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px","outline":"none","color":"#fff","borderRadius":"4px","background":"#1A3194","width":"auto","lineHeight":"40px","fontSize":"14px","height":"40px"}'>回复</el-button> -->
								<el-button class="comment-del" v-if="showIndex==item.id&&userid==item.userid" @click="discussDel(item.id)">删除</el-button>
							</div>
						</div>
						<div class="comment-content-box" v-if="item.reply">
							回复：<span class="ql-snow ql-editor" v-html="item.reply"></span>
						</div>
					</div>
				</div>
				
				<el-pagination
					background
					id="pagination" class="pagination"
					:pager-count="pageSize"
					:page-size="pageSize"
					prev-text="<"
					next-text=">"
					:hide-on-single-page="true"
					:layout='["total","prev","pager","next","sizes","jumper"].join()'
					:total="total"
					@current-change="curChange"
					@prev-click="prevClick"
					@next-click="nextClick"
					@size-change="sizeChange"
					></el-pagination>
			</div>
			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
				<el-tab-pane label="地图" name="map">
					<div>
						<iframe MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src="https://map.baidu.com/" height="600px" width="100%"></iframe>
					</div>
				</el-tab-pane>
			</el-tabs>

			<div class="idea1"></div>
			<div class="idea2"></div>
		</div>
		<div class="share_view">
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'changdixinxi',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '场地信息'
					}
				],
				title: '',
				detailBanner: [],
				userid: localStorage.getItem('frontUserid'),
				id: 0,
				detail: {},
				tabsNum: 1,
				activeName: 'second',
				form: {
					content: '',
					userid: localStorage.getItem('frontUserid'),
					nickname: localStorage.getItem('username'),
					avatarurl: '',
				},
				showIndex: -1,
				infoList: [],
				rules: {
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					],
				},
				total: 1,
				pageSize: 10,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'changdixinxi',
					userid: localStorage.getItem('frontUserid')
				},
				isStoreup: false,
				storeupInfo: {},
				buynumber: 1,
				centerType: false,
				storeupType: false,
				shareUrl: location.href,
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
			setTimeout(()=>{
				let mySwiper22 = new Swiper(".mySwiper22", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"freeMode":true,"watchSlidesVisibility":true,"watchSlidesProgress":true,"loopedSlides":5,"slidesPerView":4,"spaceBetween":10})
				let option21 = {...{"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"loopedSlides":5,"spaceBetween":10}}
				option21.thumbs = {
					swiper: mySwiper22
				}
				new Swiper(".mySwiper21", option21)
			},100)
		},
		//方法集合
		methods: {
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.changdimingcheng;
						if(this.detail.changditupian) {
							this.detailBanner = this.detail.changditupian.split(",w").length>1?[this.detail.changditupian]:this.detail.changditupian.split(',');
						}
						this.$forceUpdate();
						this.getDiscussList(1);
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
						}

					}
				});
			},
			async onAcross(acrossTable,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1){
				localStorage.setItem('crossTable',`changdixinxi`);
				localStorage.setItem('crossObj', JSON.stringify(this.detail));
				localStorage.setItem('statusColumnName',statusColumnName);
				localStorage.setItem('statusColumnValue',statusColumnValue);
				localStorage.setItem('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								type: 'error',
								message: tips,
								duration: 1500
							});
							return
						}
					}
				}
				this.$router.push({path: '/index/' + acrossTable + 'Add', query: {type: 'cross'}});
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = type;
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('changdixinxi/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'changdixinxi', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('changdixinxi/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: 1, refid: this.detail.id, tablename: 'changdixinxi', userid: localStorage.getItem('frontUserid')}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/changdixinxi', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			getDiscussList(page) {
				this.$http.get('discusschangdixinxi/list', {params: {page, limit: this.pageSize, refid: this.detail.id,sort: 'istop',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.infoList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
					}
				});
			},
			comzanChange(row){
				if(row.tuserids){
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comzanClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.userid
					}else {
						row.tuserids = this.userid
					}
					this.$http.post('discusschangdixinxi/update',row).then(rs=>{
						this.$message.success('点赞成功')
					})
				}else {
					row.thumbsupnum--
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					this.$http.post('discusschangdixinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comcaiClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.userid
					}else {
						row.cuserids = this.userid
					}
					this.$http.post('discusschangdixinxi/update',row).then(rs=>{
						this.$message.success('点踩成功')
					})
				}else {
					row.crazilynum--
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					this.$http.post('discusschangdixinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			discussEnter(index){
				this.showIndex = index
			},
			discussLeave(){
				this.showIndex = -1
			},
			discussDel(id){
				this.$confirm('是否删除此评论？').then(_ => {
					this.$http.post('discusschangdixinxi/delete',[id]).then(res=>{
						if(res.data&&res.data.code==0){
							this.addDiscussNum(1)
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									this.getDiscussList(1);
								}
							});
						}
					})
				}).catch(_ => {});
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.form.refid = this.detail.id;
						this.form.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
						this.$http.post('discusschangdixinxi/add', this.form).then(rs2 => {
							if (rs2.data.code == 0) {
								this.form.content = '';
								this.addDiscussNum(2)
								this.getDiscussList(1);
								this.$message({
									type: 'success',
									message: '评论成功!',
									duration: 1500,
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			addDiscussNum(type){
				if(type==2){
					this.detail.discussnum++
				}else if(type==1){
					if(this.detail.discussnum!=0){
						this.detail.discussnum--
					}else{
						this.detail.discussnum = 0
					}
				}
				this.$http.post('changdixinxi/update',this.detail).then(res=>{})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/changdixinxiAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此场地信息？') .then(_ => {
					this.$http.post('changdixinxi/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'changdixinxi',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
						}
					});
				}).catch(_ => {});
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 20px 0;
		margin: 0px auto;
		color: #666;
		display: flex;
		width: 1400px;
		font-size: 16px;
		justify-content: flex-start;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0 0 0 30px;
			background: none;
			flex: 1;
			display: block;
			width: 100%;
			position: relative;
			order: 2;
			.info {
				padding: 0;
				margin: 0;
				background: none;
				display: flex;
				gap: 30px;
				width: 100%;
				flex-wrap: wrap;
				.title-item {
					border: 0px solid #03abe920;
					border-radius: 4px;
					padding: 0;
					box-shadow: none;
					margin: 0 auto 3px;
					background: #fff;
					font-weight: 700;
					display: flex;
					width: 100%;
					line-height: 40px;
					justify-content: space-between;
					align-items: center;
					.detail-title {
						color: #333;
						font-weight: 600;
						font-size: 16px;
					}
					.colectBtn {
						cursor: pointer;
						padding: 0px;
						background: none;
						.icon {
							color: #666;
							font-size: inherit;
						}
						.text {
							color: #666;
							font-size: inherit;
						}
					}
					.colectBtnActive {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
					.colectBtn:hover {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
					.colectBtnActive:hover {
						.icon {
							color: #df8230;
						}
						.text {
							color: #df8230;
						}
					}
				}
				.item {
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					background: none;
					display: flex;
					width: 48%;
					border-color: #0A1E75;
					border-width: 1px 0;
					justify-content: spaceBetween;
					align-items: center;
					border-style: solid;
					.lable {
						padding: 0 20px 0 10px;
						color: #9E9E9E;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 60px;
						text-align: right;
						height: 60px;
					}
					.text {
						padding: 0;
						color: #000;
						word-break: break-all;
						flex: 1;
						font-size: inherit;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					flex-wrap: wrap;
				}
				.editBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #FFF;
					background: #0A1E75;
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
					opacity: 0.8;
				}
				.delBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #000;
					background: rgba(255, 0, 0, .1);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
					opacity: 0.8;
				}
			}
		}
		.detail-swpier2 {
			background: #fff;
			width: 560px;
			height: auto;
			order: 1;
			.swiper21 {
				width: 560px;
				height: auto;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-item {
					width: 100%;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 400px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
				.swiper-button-next {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
			}
			.swiper22 {
				width: 560px;
				height: auto;
				
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				
				.swiper-item {
					width: 100%;
					opacity: 0.4;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 100px;
					}
				}
				.swiper-slide.swiper-slide-thumb-active div {
					opacity: 1;
				}
				.swiper-button-prev {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
				.swiper-button-next {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
			}
		}
		.detail2 {
			border: 0;
			padding: 0;
			box-shadow: none;
			margin: 20px 0;
			background: #fff;
			width: 100%;
			order: 20;
			.desc-view {
				padding: 0;
				color: #1A3194;
				background: none;
				width: 100%;
				font-size: 24px;
				border-color: #1A3194;
				border-width: 1px 0;
				line-height: 50px;
				border-style: solid;
				text-align: center;
				.desc-label {
					padding: 0;
					margin: 0;
					color: #1A3194;
					background: none;
					display: inline-block;
					width: auto;
					font-size: 22px;
					line-height: 50px;
					height: 50PX;
				}
			}
			.desc-text {
				padding: 20px;
				background: #fff;
			}
			.add.commentForm {
				padding: 0;
				margin: 20px 0;
				.item {
					display: flex;
					width: 100%;
					height: auto;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #666;
						white-space: nowrap;
						letter-spacing: 4px;
						width: auto;
						font-size: 16px;
						line-height: 40px;
						text-align: right;
					}
					.editor {
						border: 1px solid #ddd;
						border-radius: 4px;
						outline: none;
						color: #333;
						width: 100%;
						font-size: 14px;
						min-height: 400px;
						line-height: 32px;
						/deep/ .avatar-uploader {
							height: 0;
							line-height: 0;
						}
					}
				}
				.commentBtn {
					padding: 0 0 0 60px;
					margin: 10px 0 0;
					width: 100%;
					height: auto;
					.submitBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0;
						margin: 0 20px 0 0;
						outline: none;
						color: #FFF;
						background: #1A3194;
						width: 100px;
						font-size: 15px;
						line-height: 44px;
						height: 44px;
					}
					.submitBtn:hover {
						opacity: 0.8;
					}
					.resetBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0;
						margin: 0 20px 0 0;
						outline: none;
						color: rgba(255, 255, 255, 1);
						background: #03cce9;
						width: 100px;
						font-size: 15px;
						line-height: 44px;
						height: 44px;
					}
					.resetBtn:hover {
						opacity: 0.8;
					}
				}
			}
			.comment-list {
				padding: 0;
				.comment-item {
					border: none;
					border-radius: 10px;
					padding: 10px 10px;
					box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.3);
					margin: 0 0 20px;
					width: 100%;
					align-items: center;
					height: auto;
					.istop {
						box-shadow: 0 4px 8px rgba(0,0,0,.1);
						top: 0;
						background: #fff;
						position: absolute;
						right: 10px;
						.icon {
							color: #000;
						}
					}
					.user {
						display: flex;
						width: 100%;
						align-items: center;
						height: auto;
						.el-image {
							border-radius: 5px;
							margin: 0 10px 0 0;
							object-fit: cover;
							width: 60px;
							height: 60px;
						}
						.name {
							color: #000000;
							font-size: 18px;
						}
					}
					.comment-time {
					}
					.comment-content-box {
						border-radius: 4px;
						padding: 8px;
						margin: 10px 0px 0px;
						word-wrap: break-word;
						color: #6F6F6F;
						background: none;
						font-size: 14px;
						line-height: 30px;
						.zancai-box {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 30px;
							.zan-item {
								margin: 0 10px 0 0;
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.zan-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.zan-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
							.cai-item {
								margin: 0 10px 0 0;
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.cai-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.cai-item:hover {
								opacity: 1;
								.icon {
								}
								.label {
								}
								.num {
								color: #ff000030;
								}
							}
						}
						.comment-btn {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 40px;
							.comment-del {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 20px;
								margin: 0 10px;
								outline: none;
								color: #525252;
								background: #F0F0F0;
								width: auto;
								font-size: 14px;
								line-height: 40px;
								height: 40px;
							}
						}
					}
				}
			}
		}
		.detail-tabs {
			border: 0px solid #ccc;
			box-shadow: none;
			padding: 0;
			margin: 20px auto 40px;
			background: #fff;
			width: 100%;
			order: 21;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 8px 0 0 40px;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20241102/1cc66366868545c191315a0fafafdc03.webp) center center / 1400px 90px no-repeat;
				border-color: #1f292f;
				border-width: 0 0 0px 0;
				line-height: 92px;
				border-style: solid;
				height: 92px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0;
				margin: 0 5px;
				color: #000;
				font-weight: 500;
				display: inline-block;
				font-size: inherit;
				line-height: 46px;
				transition: all 0s;
				background: url(http://codegen.caihongy.cn/20241102/cb13ae15f70841b7812739268ff62b9b.webp) no-repeat ;
				width: 120px;
				position: relative;
				list-style: none;
				text-align: center;
				height: 46px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				color: #FFF;
				background: url(http://codegen.caihongy.cn/20241102/f82aefb558e34bfd8b7d106bf2bcbabb.webp) no-repeat ;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				color: #FFF;
				background: url(http://codegen.caihongy.cn/20241102/f82aefb558e34bfd8b7d106bf2bcbabb.webp) no-repeat ;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px;
			}
		}
	}
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 30px;
		.share:last-of-type{
			border:none;
		}
	}
	.idea1 {
		background: #fff;
		width: 100%;
		height: 10px;
		order: 4;
	}
	.idea2 {
		background: #fff;
		width: 100%;
		height: 10px;
		order: 10;
	}
</style>
