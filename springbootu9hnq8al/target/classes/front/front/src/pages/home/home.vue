<template>
	<div class="home-preview">



		<!-- 关于我们 -->
		<div id="about" class="animate__animated">
			<div class="about_item">
				<div class="about_title_box">
					<div class="about_title">{{aboutUsDetail.title}}</div>
					<div class="about_subtitle">{{aboutUsDetail.subtitle}}</div>
				</div>
				<div class="about_img">
					<img :src="baseUrl + aboutUsDetail.picture1">
					<img :src="baseUrl + aboutUsDetail.picture2">
					<img :src="baseUrl + aboutUsDetail.picture3">
				</div>
				<div class="about_content ql-snow ql-editor" v-html="aboutUsDetail.content"></div>
				<div class="about_idea1" />
				<div class="about_idea2" />
				<div class="about_idea3" />
				<div class="about_idea4" />
				<div class="about_more" @click="toDetail('aboutusDetail',aboutUsDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 关于我们 -->

		<!-- 系统简介 -->
		<div id="system" class="animate__animated">
			<div class="system_item">
				<div class="system_title_box">
					<div class="system_title">{{systemIntroductionDetail.title}}</div>
					<div class="system_subtitle">{{systemIntroductionDetail.subtitle}}</div>
				</div>
				<div class="system_img">
					<img :src="baseUrl + systemIntroductionDetail.picture1">
					<img :src="baseUrl + systemIntroductionDetail.picture2">
					<img :src="baseUrl + systemIntroductionDetail.picture3">
				</div>
				<div class="system_content ql-snow ql-editor" v-html="systemIntroductionDetail.content"></div>
				<div class="system_idea1" />
				<div class="system_idea2" />
				<div class="system_idea3" />
				<div class="system_idea4" />
				<div class="system_more" @click="toDetail('systemintroDetail',systemIntroductionDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-gengduo1"></span>
				</div>
			</div>
		</div>
		<!-- 系统简介 -->
		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list11 index-pv1">
				<div class="left_box">
					<div class="list-item" v-for="(item,index) in newsList" :key="index" v-if="index<Number(2)" @click="toDetail('newsDetail', item)">
						<div class="img"><img :src="baseUrl + item.picture" alt=""></div>
						<div class="infoBox">
							<div class="name">{{item.title}}</div>
							<div class="time">{{item.addtime}}</div>
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
				<div class="right_box">
					<div class="list-item" :class="newsIndex11==index?'list-item-active':''" @click="newsTabClick11(index)"  v-for="(item,index) in newsList" :key="index" v-if="index>=Number(2)&&index<(Number(2) + Number(4))">
						<div class="name">{{item.title}}</div>
						<div class="list-item2" @click="toDetail('newsDetail', item)">
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendchangdixinxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">场地信息推荐</span>
				<span class="recommend_subhead">{{'changdixinxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<!-- 样式一 -->
			<div class="list list1 index-pv1">
				<div v-for="(item,index) in changdixinxiRecommend" :key="index" @click="toDetail('changdixinxiDetail', item)" class="list-item animation-box">
					<img v-if="preHttp(item.changditupian)" :src="item.changditupian.split(',')[0]" alt="" />
					<img v-else :src="baseUrl + (item.changditupian?item.changditupian.split(',')[0]:'')" alt="" />
					<div class="name line1">{{item.changdimingcheng}}</div>
					<div class="name line1">{{item.changdileixing}}</div>
				
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{item.addtime.split(' ')[0]}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏：</span>
						<span class="text">{{item.storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-liulan04"></span>
						<span class="label">浏览次数：</span>
						<span class="text">{{item.clicknum}}</span>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('changdixinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				aboutUsDetail: {},
				systemIntroductionDetail: {},
				newsList: [],
				changdixinxiRecommend: [],


				newsIndex11: Number(2),



			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getAboutUs();
			this.getSystemIntroduction();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			newsTabClick11(index){
				this.newsIndex11 = index
			},
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendchangdixinxi',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getAboutUs() {
				this.$http.get('aboutus/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.aboutUsDetail = res.data.data;
					}
				})
			},
			getSystemIntroduction() {
				this.$http.get('systemintro/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.systemIntroductionDetail = res.data.data;
					}
				})
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 7,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "changdixinxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "changdixinxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.changdixinxiRecommend = res.data.data.list;
					}
				});
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		padding: 0 0 20px 0;
		margin: 0px auto;
		flex-direction: row;
		background: #fff;
		display: flex;
		gap: 50px;
		width: 1400px;
		justify-content: center;
		flex-wrap: wrap;
		#about {
			padding: 0;
			margin: 0;
			background: none;
			width: 1010px;
			position: relative;
			height: auto;
			order: 9;
			.about_item {
				padding: 0;
				margin: 0;
				z-index: 9;
				overflow: hidden;
				background: none;
				display: block;
				width: 100%;
				flex-wrap: wrap;
				.about_title_box {
					padding: 0;
					margin: 0 0 30px 0;
					display: flex;
					border-color: #1A3194;
					background: none;
					width: 100%;
					justify-content: center;
					border-width: 1px 0 1px 0;
					align-items: center;
					position: relative;
					border-style: solid;
					text-align: left;
					height: 50px;
					.about_title {
						padding: 0;
						margin: 0;
						color: #1A3194;
						background: none;
						display: inline-block;
						width: auto;
						font-size: 23px;
						line-height: 50px;
						height: 50px;
					}
					.about_subtitle {
						margin: 0;
						color: #666;
						display: none;
						width: auto;
						font-size: 24px;
						line-height: 1.5;
					}
				}
				.about_img {
					padding: 0;
					margin: 0;
					background: none;
					display: flex;
					gap: 20px;
					width: 560px;
					float: left;
					flex-wrap: wrap;
					height: 556px;
					img:nth-child(1) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 270px;
						height: 270px;
					}
					img:nth-child(2) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 270px;
						height: 270px;
					}
					img:nth-child(3) {
						margin: 0;
						flex: 1;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 266px;
					}
				}
				.about_content {
					padding: 30px;
					margin: 0;
					overflow: hidden;
					color: #000000;
					background: none;
					width: 430px;
					font-size: 16px;
					line-height: auto;
					float: none;
					height: 556px;
				}
				.about_idea1 {
					z-index: -1;
					top: 0;
					background: url(http://codegen.caihongy.cn/20240809/4c886953e8824f4fbce50991f268e35d.png) no-repeat center top / cover;
					display: none;
					width: 100%;
					position: absolute;
					right: 0;
					height: 100%;
				}
				.about_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_more {
					border: 0;
					margin: 10px auto;
					top: 0;
					background: #150572;
					display: block;
					width: 110px;
					line-height: 32px;
					position: absolute;
					right: 10px;
					float: none;
					text-align: center;
					span:nth-child(1) {
						color: #FFF;
						font-size: 15px;
					}
					span:nth-child(2) {
						color: #FFF;
						font-size: 15px;
					}
				}
				.about_more:hover {
					cursor: pointer;
					opacity: 0.8;
				}
			}
		}
		#system {
			padding: 0;
			background: none;
			width: 100%;
			position: relative;
			height: auto;
			order: 15;
			.system_item {
				margin: 0;
				display: block;
				width: 100%;
				flex-wrap: wrap;
				.system_title_box {
					padding: 0;
					margin: 0 0 30px 0;
					background: none;
					width: 100%;
					border-color: #1A3194;
					border-width: 1px 0;
					position: relative;
					border-style: solid;
					text-align: center;
					height: 50px;
					.system_title {
						padding: 0;
						margin: 0;
						color: #1A3194;
						background: none;
						display: inline-block;
						width: auto;
						font-size: 24px;
						line-height: 50px;
						height: 50px;
					}
					.system_subtitle {
						color: #999;
						display: none;
						font-size: 24px;
					}
				}
				.system_img {
					padding: 0;
					margin: 0;
					background: none;
					display: flex;
					gap: 20px;
					width: 560px;
					float: left;
					flex-wrap: wrap;
					height: 556px;
					img:nth-child(1) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 270px;
						height: 270px;
					}
					img:nth-child(2) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 270px;
						height: 270px;
					}
					img:nth-child(3) {
						margin: 0;
						flex: 1;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 266px;
					}
				}
				.system_content {
					padding: 30px;
					margin: 0;
					overflow: hidden;
					color: #000000;
					background: none;
					width: 430px;
					font-size: 16px;
					line-height: auto;
					float: none;
					height: 556px;
				}
				.system_idea1 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.system_more {
					border: 0;
					margin: 10px auto;
					top: 0;
					background: #150572;
					display: block;
					width: 110px;
					line-height: 32px;
					position: absolute;
					right: 10px;
					float: none;
					text-align: center;
					span:nth-child(1) {
						color: #FFF;
						font-size: 15px;
					}
					span:nth-child(2) {
						color: #FFF;
						font-size: 15px;
					}
				}
				.system_more:hover {
					cursor: pointer;
					opacity: 0.8;
				}
			}
		}
		.news {
			padding: 0;
			margin: 0;
			background: none;
			width: 1010px;
			position: relative;
			order: 12;
			.news_title_box {
				padding: 0;
				margin: 0;
				background: none;
				width: 100%;
				position: relative;
				text-align: center;
				height: 50px;
				.news_title {
					padding: 0;
					margin: 0;
					color: #1A3194;
					background: none;
					display: inline-block;
					width: 100%;
					font-size: 24px;
					border-color: #1A3194;
					border-width: 1px 0;
					line-height: 50px;
					border-style: solid;
					height: 50px;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 0;
				margin: 50px 0 0;
				overflow: hidden;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				.left_box {
					width: 48%;
					.list-item {
						border: 1px dashed #bbbbbb;
						cursor: pointer;
						padding: 0 0 25px;
						margin: 0 0 24px;
						overflow: hidden;
						display: flex;
						width: 100%;
						border-width: 0 0 1px;
						justify-content: space-between;
						.img {
							width: 185px;
							height: 150px;
							img {
								object-fit: cover;
								width: 100%;
								transition: all 0.3s;
								height: 100%;
							}
						}
						.infoBox {
							width: calc(100% - 205px);
							.name {
								overflow: hidden;
								color: #666;
								white-space: nowrap;
								font-weight: 600;
								width: 100%;
								font-size: 16px;
								line-height: 26px;
								text-overflow: ellipsis;
							}
							.time {
								margin: 10px 0;
								color: #6a6a6a;
								font-size: 14px;
							}
							.desc {
								overflow: hidden;
								color: #858585;
								line-height: 24px;
								height: 72px;
							}
						}
					}
					.list-item:hover {
						.img {
							img {
								transform: scale(1.05);
							}
						}
						.infoBox {
							.name {
								color: #150572;
							}
						}
					}
				}
				.right_box {
					padding: 0 0 0 30px;
					overflow: hidden;
					width: 48%;
					height: 418px;
					.list-item {
						position: relative;
						.name {
							cursor: pointer;
							padding: 0 50px 0 25px;
							overflow: hidden;
							color: #353535;
							white-space: nowrap;
							font-weight: normal;
							width: 100%;
							font-size: 15px;
							line-height: 40px;
							position: relative;
							text-overflow: ellipsis;
						}
						.name::before {
							color:  #666;
							top: 0;
							font-weight: normal;
							font-size: 22px;
							line-height: 40px;
							position: absolute;
							right: 25px;
							content: "+";
						}
						.list-item2 {
							border: 1px dashed #bbbbbb;
							padding: 0 25px;
							margin: 0;
							overflow: hidden;
							display: block;
							border-width: 0 0 1px;
							opacity: 0;
							transition: all 0.5s;
							height: 0;
							.desc {
								cursor: pointer;
								margin: 10px 0 0;
								overflow: hidden;
								color: #909090;
								font-size: 14px;
								line-height: 25px;
								height: 200px;
							}
						}
					}
					.list-item.list-item-active {
						.name {
							color: #FFF;
							background: #150572;
						}
						.name::before {
							color: #fff;
							content: "-";
						}
						.list-item2 {
							padding: 0 25px 16px;
							margin: 0 0 10px;
							display: block;
							opacity: 1;
							transition: all 0.5s;
							height: 174px;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 0;
				top: 10px;
				background: #150572;
				display: block;
				width: 110px;
				line-height: 32px;
				position: absolute;
				right: 0;
				text-align: center;
				.text {
					color: #fff;
					font-size: 15px;
				}
				.icon {
					color: #fff;
					font-size: 15px;
				}
			}
		}
		.recommend {
			padding: 0;
			margin: 0;
			background: #fff;
			width: 340px;
			position: relative;
			order: 2;
			.recommend_title_box {
				padding: 0;
				margin: 0;
				background: none;
				width: 100%;
				border-color: #1A3194;
				border-width: 1px 0 1px 0;
				border-style: solid;
				text-align: center;
				height: 50px;
				.recommend_title {
					padding: 0;
					margin: 0;
					color: #1A3194;
					background: none;
					display: inline-block;
					width: auto;
					font-size: 24px;
					line-height: 50px;
					height: 50px;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list1 {
				scrollbar-width: thin;
				border: 1px solid #150572;
				padding: 10px 0 0;
				max-height: 950px;
				margin: 30px 0 0 0;
				overflow: auto;
				background: none;
				width: 100%;
				height: 950px;
				.list-item {
					cursor: pointer;
					padding: 0 20px 20px 20px;
					margin: 0;
					color: #888;
					background: none;
					display: inline-block;
					width: 100%;
					font-size: 14px;
					position: relative;
					height: auto;
					img {
						margin: 0 0 5px;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 160px;
					}
					.name {
						padding: 0 10px;
						overflow: hidden;
						color: #333;
						white-space: nowrap;
						width: 100%;
						font-size: 16px;
						line-height: 30px;
						text-overflow: ellipsis;
					}
					.price {
						padding: 0 10px;
						color: #f00;
						font-size: 16px;
						line-height: 1.5;
					}
					.time_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: inherit;
							display: none;
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
						padding: 0 10px;
						display: inline-block;
						.icon {
							margin: 0 2px 0 0;
							color: inherit;
							display: none;
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
						padding: 0 10px;
						display: inline-block;
						.icon {
							margin: 0 2px 0 0;
							color: inherit;
							display: none;
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
						padding: 0 10px;
						display: inline-block;
						.icon {
							margin: 0 2px 0 0;
							color: inherit;
							display: none;
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
						padding: 0 10px;
						display: inline-block;
						.icon {
							margin: 0 2px 0 0;
							color: inherit;
							display: none;
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
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 0;
				top: 10px;
				background: #f7db61;
				display: none;
				width: 110px;
				line-height: 32px;
				position: absolute;
				right: 0;
				text-align: center;
				.text {
					color: #333;
					font-size: 15px;
				}
				.icon {
					color: #333;
					font-size: 15px;
				}
			}
		}
	}
</style>
