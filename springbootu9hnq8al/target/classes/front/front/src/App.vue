<template>
	<router-view></router-view>
</template>

<script>
	export default {
		name: 'App',
		created() {
			
		}
	}
</script>

<style>
	* {
		box-sizing: border-box;
	}

	html,
	body {
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
	}

	[v-cloak] {
		display: none
	}

	.el-tabs__item {
		font-size: 18px;
	}

	.el-loading-spinner .el-loading-text {
		color: #4169E1;
	}

	.el-loading-spinner .path {
		stroke: #4169E1;
	}

	.el-breadcrumb {
		line-height: 60px;
		font-size: 18px;
	}

	.ql-editor {
		/*height: 500px;*/
	}

	.ql-snow .ql-tooltip[data-mode="link"]::before {
		content: "请输入链接地址:";
	}

	.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
		border-right: 0px;
		content: "保存";
		padding-right: 0px;
	}

	.ql-snow .ql-tooltip[data-mode="video"]::before {
		content: "请输入视频地址:";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item::before {
		content: "14px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
		content: "10px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
		content: "18px";
	}

	.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
	.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
		content: "32px";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item::before {
		content: "文本";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
		content: "标题1";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
		content: "标题2";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
		content: "标题3";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
		content: "标题4";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
		content: "标题5";
	}

	.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
	.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
		content: "标题6";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item::before {
		content: "标准字体";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
		content: "衬线字体";
	}

	.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
	.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
		content: "等宽字体";
	}
	.breadcrumb-preview {
		border-radius: 0;
		padding: 20px 0;
		margin: 0px auto 30px auto;
		background: none;
		width: 1400px;
		border-bottom: 2px dashed #0812A3;
	}
	.breadcrumb-preview .el-breadcrumb {
		font-size: 16px;
		line-height: 1;
		
	}
	.breadcrumb-preview .el-breadcrumb .el-breadcrumb__separator {
		margin: 0 50px;
		color: #000;
		font-weight: 500;
	}
	.breadcrumb-preview .el-breadcrumb .item1 .el-breadcrumb__inner a {
		color: #000;
		display: inline-block;
	}
	.breadcrumb-preview .el-breadcrumb .item2 .el-breadcrumb__inner a {
		color: #333;
		display: inline-block;
	}
	.breadcrumb-preview .el-breadcrumb .item3 .el-breadcrumb__inner a {
		color: #666;
		display: inline-block;
	}
	.back_box {
		border-radius: 0px;
		padding: 0;
		margin: 10px auto 0;
		background: #fff;
		display: flex;
		width: 1400px;
		justify-content: flex-start;
	}
	.back_box .backBtn {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 20px;
		margin: 0;
		background: #1A3194;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
	}
	.back_box .backBtn .icon {
		margin: 0 4px 0 0;
		color: #fff;
		font-size: 14px;
	}
	.back_box .backBtn .text {
		color: #fff;
	}
	.back_box .backBtn:hover {
		opacity: 0.8;
	}
	.back_box .backBtn:hover .icon {
	}
	.back_box .backBtn:hover .text {
	}
	/* 分页样式 */
	#pagination {
		padding: 10px 0;
		margin: 20px auto;
		overflow: hidden;
		color: #333;
		white-space: nowrap;
		font-weight: 500;
		width: 100%;
		clear: both;
		font-size: 16px;
		text-align: left;
		order: 50;
	}
	#pagination.el-pagination .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 48px;
	}
	
	#pagination.el-pagination .btn-prev {
		border: none;
		padding: 0;
		margin: 0;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 0px 5px 5px 0px ;
		box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);
		background: #0A1E75;
		min-width: 50px;
		height: 50px;
	}
	
	#pagination.el-pagination .btn-next {
		border: none;
		padding: 0;
		margin: 0;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 0px 5px 5px 0px ;
		box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);
		background: #0A1E75;
		min-width: 50px;
		height: 50px;
	}
	
	#pagination.el-pagination .btn-prev:disabled {
		border: none;
		padding: 0;
		margin: 0;
		color: #000;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 5px 0 0 5px;
		box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);
		background: none;
		min-width: 50px;
		height: 50px;
	}
	
	#pagination.el-pagination .btn-next:disabled {
		border: none;
		padding: 0;
		margin: 0;
		color: #000;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		border-radius: 5px 0 0 5px;
		box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);
		background: none;
		min-width: 50px;
		height: 50px;
	}
	
	#pagination.el-pagination .el-pager {
		padding: 10px 12px;
		box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);
		margin: 0;
		background: #FFFEFF;
		display: inline-block;
		vertical-align: top;
	}
	
	#pagination.el-pagination .el-pager .number {
		cursor: pointer;
		border-radius: 5px;
		padding: 0;
		margin: 0 10px 0 0;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 12px;
		line-height: 30px;
		text-align: center;
		height: 30px;
	}
	
	#pagination.el-pagination .el-pager .number:hover {
		cursor: default;
		border-radius: 50%;
		padding: 0;
		margin: 0 10px 0 0;
		color: #fff;
		background: #0A1E75;
		display: inline-block;
		vertical-align: top;
		font-size: 12px;
		line-height: 30px;
		text-align: center;
		height: 30px;
	}
	
	#pagination.el-pagination .el-pager .number.active {
		cursor: default;
		border-radius: 50%;
		padding: 0;
		margin: 0 10px 0 0;
		color: #fff;
		background: #0A1E75;
		display: inline-block;
		vertical-align: top;
		font-size: 12px;
		line-height: 30px;
		text-align: center;
		height: 30px;
	}
	
	#pagination.el-pagination .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	#pagination.el-pagination .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	#pagination.el-pagination .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	#pagination.el-pagination .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 28px;
		text-align: center;
	}
	
	#pagination.el-pagination .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	#pagination.el-pagination .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: inherit;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
</style>