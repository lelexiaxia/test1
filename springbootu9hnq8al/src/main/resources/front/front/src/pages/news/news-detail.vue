<template>
	<div class="news-detail-box">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="news-detail-view">
			<div class="title-box">
				<div>公告资讯</div>
			</div>
			<div class="news-detail">
				<div class="detail-title">{{detail.title}}</div>
				<div class="infoBox">
					<div class="time_item">
						<span class="icon iconfont "></span>
						<span class="label">发布时间：</span>
						<span class="text">{{detail.addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont "></span>
						<span class="label">发布人：</span>
						<span class="text">{{detail.name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont "></span>
						<span class="label">点赞：</span>
						<span class="text">{{detail.thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont "></span>
						<span class="label">收藏：</span>
						<span class="text">{{detail.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont "></span>
						<span class="label">浏览次数：</span>
						<span class="text">{{detail.clicknum}}</span>
					</div>
				</div>
				<div class="operate">
					<div class="zan-btn" @click="zanClick" >
						<span class="icon iconfont" :class="zanType?'icon-guanzhu-zhihui':'icon-guanzhu-zhihui'" ></span>
						<span class="text" >{{zanType?'取消点赞':'点赞'}}</span>
					</div>
					<div class="collect-btn" @click="collectClick" >
						<span class="icon iconfont" :class="collectType?'icon-shoucang10':'icon-shoucang10'" ></span>
						<span class="text" >{{collectType?'取消收藏':'收藏'}}</span>
					</div>
				</div>
				
				<div class="content-detail ql-snow ql-editor" v-html="detail.content"></div>
			</div>
			<!-- option -->
			<div class="option-box">
				<div class="prev-btn" @click="prepDetailClick">
					<span class="text">上一篇：prev</span>
					<span class="icon iconfont "></span>
				</div>
				<div class="next-btn" @click="nextDetailClick">
					<span class="text">下一篇：next</span>
					<span class="icon iconfont "></span>
				</div>
			</div>

			<!-- 热门文章 -->
			<div class="hot">
				<div class="hot-title">热门文章</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 推荐文章 -->
			<div class="news">
				<div class="news-title">推荐文章</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toDetail(item.id)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="news-name">{{ item.title }}</div>
						<div class="news-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				id: 0,
				detail: {},
				zanType: false,
				zanForm: {},
				collectType:false,
				collectForm: {},
				baseUrl:'',
				hotList: [],
				recommendList: [],
				categoryList: [],
				currentIndex: 0,
				allList: [],
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			this.id = this.$route.query.id
			this.baseUrl = this.$config.baseUrl;
			this.getCategoryList()
			this.getDetail()
			this.getNewsList()
			this.getHotList()
			this.getRecommendList()
		},
		watch:{
			$route(newValue) {
				this.id = this.$route.query.id
			    this.getDetail()
			}
		},
		methods: {
			backClick() {
				if(this.storeupType){
					history.back()
				}else {
					this.$router.push({path: '/index/news'});
				}
			},
			getNewsList() {
				let params = {page:1, limit: 100,sort:'addtime',order:'desc'};
				this.$http.get('news/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].id == this.id){
								this.currentIndex = Number(x)
								break
							}
						}
						this.allList = res.data.data.list
					}
				});
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$message.error('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$message.error('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data;
					}
				});
			},
			// 获取热门列表
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			// 获取推荐列表
			getRecommendList(){
				let url = 'news/autoSort'
				if(localStorage.getItem('frontToken')){
					url = 'news/autoSort2'
				}
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get(url, {params: params}).then(res => {
					if (res.data.code == 0) {
						this.recommendList = res.data.data.list;
					}
				});
			},
			// 跳转详情
			toDetail(id){
				this.$router.push({path: '/index/newsDetail', query: {id: id}});
			},
			getDetail(){
				this.$http.get(`news/detail/${this.id}`,{}).then(res=>{
					if(res.data&&res.data.code==0){
						this.detail = res.data.data
						window.scrollTo(0, 100)
						this.getZan()
						this.getCollect()
					}
				})
			},
			getZan() {
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '21',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.zanType = true
							this.zanForm = res.data.data.list[0]
						}else{
							this.zanType = false
						}
					}
				})
			},
			getCollect(){
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '1',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.collectType = true
							this.collectForm = res.data.data.list[0]
						}else{
							this.collectType = false
						}
					}
				})
			},
			zanClick() {
				if(this.zanType){
					this.$http.post('storeup/delete', [this.zanForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.thumbsupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '21',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('点赞成功')
							this.detail.thumbsupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getZan()
						}
					})
				}
			},
			collectClick(){
				if(this.collectType){
					this.$http.post('storeup/delete', [this.collectForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.storeupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getCollect()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture,
						refid: this.detail.id,
						type: '1',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.detail.storeupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.$message.success('收藏成功')
							this.getCollect()
						}
					})
				}
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	
	.news-detail-box {
				margin: 0px auto;
				color: #333;
				background: #fff;
				width: 1400px;
				font-size: 15px;
				position: relative;
				.news-detail-view {
						flex-direction: column;
						display: flex;
						width: 100%;
						align-items: flex-start;
						flex-wrap: wrap;
						.title-box {
								display: none;
								width: 100%;
								height: auto;
								div {
										margin: 10px 0;
										color: #333;
										background: none;
										font-size: 24px;
										line-height: 54px;
										text-align: center;
									}
			}
			.news-detail {
								padding: 20px;
								width: 100%;
								position: relative;
								.detail-title {
										color: #333;
										font-weight: 600;
										font-size: 22px;
										line-height: 40px;
										text-align: center;
									}
				.infoBox {
										margin: 20px 0 0;
										color: #aaa;
										display: flex;
										width: 100%;
										border-color: #1A3194;
										border-width: 1px 0;
										justify-content: center;
										align-items: center;
										border-style: solid;
										height: 60px;
										.time_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.text {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
					}
					.publisher_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.text {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
					}
					.like_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.text {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
					}
					.collect_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.text {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
					}
					.view_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
						.text {
														color: inherit;
														font-size: inherit;
														line-height: 1.5;
													}
					}
				}
				.operate {
										padding: 10px 0px;
										display: block;
										width: 100%;
										justify-content: flex-end;
										align-items: center;
										text-align: right;
										.zan-btn {
												border-radius: 20px;
												padding: 0 10px;
												margin: 0 0px 0 0;
												background: none;
												display: inline-block;
												line-height: 40px;
												height: 40px;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
													}
						.text {
														color: inherit;
														font-size: inherit;
													}
					}
					.zan-btn:hover {
												cursor: pointer;
												.icon {
													}
						.text {
													}
					}
					.collect-btn {
												border-radius: 20px;
												padding: 0 10px;
												margin: 0 0 0 10px;
												background: none;
												display: inline-block;
												line-height: 40px;
												height: 40px;
												.icon {
														margin: 0 2px 0 0;
														color: inherit;
														font-size: inherit;
													}
						.text {
														color: inherit;
														font-size: inherit;
													}
					}
					.collect-btn:hover {
												cursor: pointer;
												padding: 0 10px;
												margin: 0 0 0 10px;
												.icon {
													}
						.text {
													}
					}
				}
				.content-detail {
										color: inherit;
										width: 100%;
										font-size: inherit;
										line-height: 1.8;
										height: auto;
									}
			}
			.option-box {
								padding: 0 20px;
								display: flex;
								width: 100%;
								justify-content: center;
								height: auto;
								.prev-btn {
										padding: 0 40px;
										color: #fff;
										background: #A6ABC1;
										.text {
												color: inherit;
												font-size: inherit;
												line-height: 40px;
											}
					.icon {
												color: inherit;
												font-size: inherit;
												line-height: 40px;
											}
				}
				.prev-btn:hover {
										cursor: pointer;
										opacity: 0.8;
										.text {
											}
					.icon {
											}
				}
				.next-btn {
										padding: 0 40px;
										color: #fff;
										background: #1A3194;
										.text {
												color: inherit;
												font-size: inherit;
												line-height: 40px;
											}
					.icon {
												color: inherit;
												font-size: inherit;
												line-height: 40px;
											}
				}
				.next-btn:hover {
										cursor: pointer;
										opacity: 0.8;
										.text {
											}
					.icon {
											}
				}
			}
			.hot {
								margin: 20px auto;
								background: #fff;
								width: 100%;
								height: auto;
								.hot-title {
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
									}
				.hot-list {
										padding: 150px 0 0 0;
										margin: 0;
										background: none;
										display: flex;
										width: 100%;
										justify-content: space-between;
										flex-wrap: wrap;
										height: auto;
										.hot-item {
												cursor: pointer;
												border: 2px solid #150572;
												padding: 0;
												background: none;
												display: flex;
												width: 340px;
												justify-content: center;
												align-items: center;
												flex-wrap: wrap;
												height: 208px;
												img {
														border-radius: 30px;
														margin: -115px 0 0 0;
														object-fit: cover;
														display: block;
														width: 300px;
														height: 229px;
													}
						.hot-name {
														padding: 0;
														overflow: hidden;
														color: #000000;
														white-space: nowrap;
														width: 100%;
														font-size: 20px;
														line-height: 30px;
														text-overflow: ellipsis;
														text-align: center;
													}
						.hot-time {
														padding: 0;
														color: #9E9E9E;
														font-size: 16px;
														line-height: 20px;
														text-align: center;
													}
					}
				}
			}
			.news {
								padding: 40px 0;
								background: none;
								flex: 1;
								width: 100%;
								height: auto;
								.news-title {
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
									}
				.news-list {
										padding: 150px 0 0 0;
										margin: 0;
										background: none;
										display: flex;
										width: 100%;
										justify-content: space-between;
										flex-wrap: wrap;
										height: auto;
										.news-item {
												cursor: pointer;
												border: 2px solid #150572;
												padding: 0;
												background: none;
												display: flex;
												width: 340px;
												justify-content: center;
												align-items: center;
												flex-wrap: wrap;
												height: 208px;
												img {
														border-radius: 30px;
														margin: -115px 0 0 0;
														object-fit: cover;
														display: block;
														width: 300px;
														height: 229px;
													}
						.news-name {
														padding: 0;
														overflow: hidden;
														color: #000000;
														white-space: nowrap;
														width: 100%;
														font-size: 20px;
														line-height: 30px;
														text-overflow: ellipsis;
														text-align: center;
													}
						.news-time {
														padding: 0;
														color: #9E9E9E;
														font-size: 16px;
														line-height: 20px;
														text-align: center;
													}
					}
				}
			}
		}
	}
</style>