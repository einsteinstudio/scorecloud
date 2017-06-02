webpackJsonp([7],{

/***/ 0:
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(44);


/***/ },

/***/ 44:
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(45);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(12)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/less-loader/index.js!./lianxi.less", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/less-loader/index.js!./lianxi.less");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },

/***/ 45:
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(11)();
	// imports


	// module
	exports.push([module.id, "body {\n  font-family: \"Helvetica Neue\", Helvetica, \"Hiragino Sans GB\", \"Microsoft YaHei\", Arial, sans-serif;\n  background: #f3f3f3;\n}\na {\n  color: #000;\n  text-decoration: none;\n}\nsection.news_title {\n  font-size: 20px;\n  margin: 10px auto;\n  margin: 0;\n  padding: 0;\n  white-space: normal;\n  font-weight: bold;\n}\np.news_title {\n  margin-top: 20px;\n  margin-bottom: 0;\n}\ndiv.news_source {\n  color: #8c8c8c;\n  font-size: 14px;\n  margin: 10px auto;\n  margin: 0;\n  padding: 0;\n  white-space: normal;\n}\nsection.img_border1 {\n  margin: 20px auto;\n  padding: 0;\n  text-align: center;\n}\nsection.img_border2 {\n  margin: 0;\n  padding: 0;\n  border: 1px solid #a9a9a9;\n  text-align: center;\n  box-shadow: 0 0 8px #787878;\n}\ndiv.page_content {\n  margin: 10px;\n}\ndiv.content {\n  margin-top: 10px;\n}\n.content img {\n  max-width: 100%!important;\n  height: auto;\n  margin: 10px auto;\n}\nspan.info {\n  text-align: left;\n  font-size: 14px;\n}\n.content p {\n  text-align: left;\n  font-size: 14px;\n  line-height: 14px;\n}\nfooter.copyright {\n  text-align: center;\n  color: #999;\n  font-size: 10px;\n  position: fixed;\n  bottom: 10px;\n  left: 0;\n  width: 100%;\n}\n", ""]);

	// exports


/***/ }

});