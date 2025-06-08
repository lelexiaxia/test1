<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'≡'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<div v-if="newsList.length" class="list8 index-pv1">
				<div class="list-body-top">
					<div class="list-item1" @click="toNewsDetail(newsList[0])">
						<img :src="baseUrl + newsList[0].picture">
						<div class="infoBox">
							<div class="name">{{newsList[0].title}}</div>
							<div class="desc">{{newsList[0].introduction}}</div>
							<div class="time">{{newsList[0].addtime.split(' ')[0]}}</div>
						</div>
					</div>
					<div class="list-body-right" v-if="newsList.length > 1">
						<div class="list-item" v-for="item,index in newsList" v-if="index > 0 && index < 5" @click="toNewsDetail(item)">
							<div class="time_item">
								<div class="day">{{item.addtime.split(" ")[0].split("-")[2]}}</div>
								<div class="year">{{item.addtime.split(" ")[0].split("-")[0] + '-' + item.addtime.split(" ")[0].split("-")[1]}}</div>
							</div>
							<div class="infoBox">
								<div class="name">{{item.title}}</div>
								<div class="desc">{{item.introduction}}</div>
								<span class="icon iconfont icon-jiantou37"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body" v-if="newsList.length > 4">
					<div class="list-item" v-for="item,index in newsList" v-if="index > 4" @click="toNewsDetail(item)">
						<div class="name">{{item.title}}</div>
						<div class="time">{{item.addtime.split(" ")[0]}}</div>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				@current-change="curChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 最新动态 -->
			<div class="news">
				<div class="news-title">最新动态</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toNewsDetail(item)">
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
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '公告资讯'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
				recommendList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
			this.getRecommendList()
		},
		watch:{
			$route(newValue){
				this.getCategoryList()
			}
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						if(this.$route.query.homeFenlei){
							for(let i=0;i<this.categoryList.length;i++) {
								if(this.$route.query.homeFenlei == this.categoryList[i].typename) {
									this.categoryIndex = i + 1;
									const currentRoute = this.$route;
									const routeWithoutQuery = { ...currentRoute };
									delete routeWithoutQuery.query;
									this.$router.replace(routeWithoutQuery)
									break;
								}
							}
						}
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
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
			curChange(page) {
				this.getNewsList(page);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				margin: 0px auto;
				color: #333;
				background: none;
				width: 1400px;
				font-size: 16px;
				position: relative;
				.list-form-pv {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid #1A3194;
										border-radius: 4px;
										padding: 0 10px;
										margin: 0;
										color: #333;
										width: auto;
										font-size: 16px;
										line-height: 42px;
										min-width: 350px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 4px;
								padding: 0px 15px;
								margin: 0 10px 0 0;
								color: #fff;
								background: #1A3194;
								width: auto;
								font-size: inherit;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 3px 0 0;
										color: #fff;
										font-size: inherit;
									}
			}
		}
		.category-list {
						padding: 10px 0 0 20px;
						margin: 20px 0;
						background: url(http://codegen.caihongy.cn/20241102/1cc66366868545c191315a0fafafdc03.webp) center center / 1400px 90px no-repeat;
						display: flex;
						width: 100%;
						align-items: center;
						height: 92px;
						.item {
								cursor: pointer;
								border-radius: 4px;
								padding: 0;
								margin: 0;
								color: inherit;
								background: url(http://codegen.caihongy.cn/20241102/cb13ae15f70841b7812739268ff62b9b.webp) no-repeat ;
								display: flex;
								font-size: 16px;
								justify-content: center;
								align-items: center;
								min-width: 120px;
								height: 46px;
							}
			
			.item:hover {
								color: #FFF;
								background: url(http://codegen.caihongy.cn/20241102/f82aefb558e34bfd8b7d106bf2bcbabb.webp) no-repeat ;
							}
			
			.item.active {
								color: #FFF;
								background: url(http://codegen.caihongy.cn/20241102/f82aefb558e34bfd8b7d106bf2bcbabb.webp) no-repeat ;
							}
		}
		.list8 {
						padding: 20px 0 40px 0;
						background: #fff;
						width: 100%;
						height: auto;
						.list-body-top {
								display: flex;
								width: 100%;
								height: auto;
								.list-item1 {
										border-radius: 10px;
										overflow: hidden;
										width: 670px;
										position: relative;
										height: 540px;
										img {
												object-fit: cover;
												display: block;
												width: 100%;
												height: 100%;
											}
					.infoBox {
												padding: 10px;
												z-index: 9;
												left: 0;
												bottom: 0;
												background: rgba(0,0,0,.3);
												width: 100%;
												position: absolute;
												.name {
														color: #fff;
														font-size: 16px;
														line-height: 32px;
													}
						.desc {
														color: #fff;
														font-size: 14px;
														line-height: 1.5;
													}
						.time {
														color: #fff;
														font-size: 14px;
														line-height: 1.5;
													}
					}
				}
				.list-body-right {
										padding: 0 0 0 20px;
										flex: 1 1 auto;
										width: 50%;
										height: 540px;
										.list-item {
												padding: 0;
												margin: 0 0 20px 0;
												display: flex;
												width: 100%;
												justify-content: center;
												align-items: center;
												height: 120px;
												.time_item {
														border: none;
														padding: 10px 0;
														margin: 0 20px 0 0;
														flex-direction: column;
														background: url(http://codegen.caihongy.cn/20241103/7ef7920bb3a443bbb554ead594d99aec.webp) no-repeat center;
														display: flex;
														width: 182px;
														justify-content: center;
														align-items: center;
														height: 120px;
														.day {
																color: #1A3194;
																font-weight: 700;
																font-size: 24px;
																line-height: 1.5;
															}
							.year {
																color: #1A3194;
																font-size: 24px;
																line-height: 1.5;
															}
						}
						.infoBox {
														flex: 1;
														height: 100%;
														.name {
																color: #000000;
																font-weight: 700;
																font-size: 16px;
																line-height: 30px;
															}
							.desc {
																padding: 24px 0 0 0;
																overflow: hidden;
																color: #575757;
																font-size: 14px;
																line-height: 24px;
																height: 72px;
															}
							.icon {
																color: #575757;
																font-size: 14px;
																line-height: 20px;
																float: right;
															}
						}
					}
				}
			}
			.list-body {
								margin: 20px 0 0 0;
								width: 100%;
								height: auto;
								.list-item {
										padding: 20px 10px;
										display: flex;
										width: 100%;
										border-color: #0812A3;
										border-width: 0 0 2px;
										justify-content: space-between;
										border-style: dashed;
										.name {
												color: #000;
												font-weight: 600;
												font-size: 16px;
												line-height: 40px;
											}
					.time {
												color: #575757;
												font-size: 14px;
												line-height: 40px;
											}
				}
			}
		}
		.hot {
						margin: 20px 0;
						overflow: hidden;
						background: none;
						width: 100%;
						clear: both;
						height: auto;
						order: 100;
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
						margin: 0;
						overflow: hidden;
						background: none;
						width: 100%;
						clear: both;
						height: auto;
						order: 100;
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
												padding: 0 10px;
												color: #999;
												font-size: inherit;
												line-height: 24px;
												text-align: right;
											}
				}
			}
		}
	}
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(0.98) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
