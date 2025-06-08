export default {
	baseUrl: 'http://localhost:8080/springbootu9hnq8al/',
	name: '/springbootu9hnq8al',
	indexNav: [
// frontMenu com.jlwl.entity.Menu@774d0b3e
		{
			name: '场地信息',
			url: '/index/changdixinxi',
		},
// frontMenu com.jlwl.entity.Menu@48262b6a
		{
			name: '比赛信息',
			url: '/index/bisaixinxi',
		},
// frontMenu com.jlwl.entity.Menu@63c3942c
		{
			name: '装备信息',
			url: '/index/zhuangbeixinxi',
		},
// frontMenu com.jlwl.entity.Menu@734f55c7
		{
			name: '比赛成绩',
			url: '/index/bisaichengji',
		},
		{
			name: '公告资讯',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '公告资讯',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
