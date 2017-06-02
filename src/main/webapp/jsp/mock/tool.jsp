<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
	<meta name="baidu_union_verify" content="11b893f7f2790018538c635fb38896f2">
    <link type="text/css" rel="stylesheet" href="../../plugins/foundation/css/foundation.css">
    <script src="../../plugins/jquery/jquery-1.8.3.js"></script>
    <script src="../../plugins/md5.js"></script>
    <script src="../../js/mock/jsl.format.js"></script>
    <script src="../../js/mock/jsl.interactions.js"></script>
    <script src="../../js/mock/jsl.parser.js"></script>
    <script src="../../js/mock/json2.js"></script>
    <script src="../../js/mock/tool.js"></script>
    <style type="text/css">
        .row {
            max-width: 80rem;
        }
        nav {
            position:fixed;
            left:100px;
            top:100px;
        }
        .header {
            position:fixed;
        }
        .right{
            padding-top:100px;
        }
    </style>
<body>
<div class="header">
<h2>小工具</h2>
</div>
    <div class="row">
        <div class="large-3 columns">
            <nav>
                <ul class="side-nav">
                <li><a href="#F1">Base64编码解码</a></li>
                <li><a href="#F2">URL编码解码</a></li>
                <li><a href="#F3">JSON代码校验格式化</a></li>
                <li><a href="#F4">XML代码校验格式化</a></li>
                <li><a href="#F41">MD5</a></li>
                <li><a href="#F5">公私钥</a></li>
                <li><a href="#F51">签名</a></li>
                <li><a href="#F52">验签</a></li>
                <li><a href="#F6">16进制与base64互转</a></li>
                <li><a href="#F7">身份证校验</a></li>
                </ul>
            </nav>
        </div>
        <div class="large-9 columns right">
            <div>
            <a name="F1"></a><h3>Base64编码解码</h3>
            <label>请输入要进行编码或解码的字符：<textarea class="live-input"  rows="6" id="decode-input"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="decode-base-btn">解码</a>
            <a href="javascript:void(0);" class="button small" id="encode-base-btn">编码</a>
            <label>Base64编码或解码结果：<textarea  id="decode-base-output" rows="6"></textarea></label>
            </div>
            <div>
            <a name="F2"></a><h3>URL编码解码</h3>
            <label>请输入要进行编码或解码的字符：<textarea class="live-input"  rows="6" id="url-input"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="url-decode-btn">解码</a>
            <a href="javascript:void(0);" class="button small" id="url-encode-btn">编码</a>
            <label>URL编码或解码结果：<textarea id="url-output"  rows="6"></textarea></label>
            </div>
            <div>
            <a name="F3"></a><h3>JSON代码校验格式化</h3>
            <label>JSON串<textarea   rows="12" id="json_input"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="json-btn">格式化</a>
            </div>
            <div>
            <a name="F4"></a><h3>XML代码校验格式化</h3>
            <label>XML串<textarea   rows="6"></textarea></label>
            <a href="javascript:void(0);" class="button small">解码</a>
            <a href="javascript:void(0);" class="button small">编码</a>
            <label>检验结果：<textarea   rows="6"></textarea></label>
    </div>
    <div>
            <a name="F41"></a><h3>MD5</h3>
            <label>请输入要进行MD5的字符串：<textarea class="live-input"  rows="3" id="md5-input"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="md5-upper-btn">生成大写32位MD5</a>
            <a href="javascript:void(0);" class="button small" id="md5-lower-btn">生成小写32位MD5</a>
            <label>MD5结果：<textarea  id="md5-output" rows="1"></textarea></label>
    </div>
    <div>
            <a name="F5"></a><h3>公私钥</h3>
            <label>公钥：<textarea  id="pair-pub" rows="6"></textarea></label>
            <label>私钥：<textarea  id="pair-pri" rows="10"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="gen-hex-btn">生成16进制</a>
            <a href="javascript:void(0);" class="button small" id="gen-base-btn">生成Bas64格式</a>
            <a href="javascript:void(0);" class="button small" id="verify-hex-btn">验16进制公私钥匹配</a>
            <a href="javascript:void(0);" class="button small" id="verify-base-btn">验base64公私钥匹配</a>
    </div>
    <div>
            <a name="F51"></a><h3>签名</h3>
            <label>私钥：<textarea id="sign-pri" class="live-input" rows="10"></textarea></label>
            <label>原串：<textarea  id="sign-src" class="live-input" rows="6"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="hex-sign-btn">生成16进制签名</a>
            <a href="javascript:void(0);" class="button small" id="base-sign-btn">生成base64签名</a>
            <label>签名串：<textarea  id="sign-sign" rows="6"></textarea></label>
    </div>
    <div>
            <a name="F52"></a><h3>验签</h3>
            <label>原串：<textarea  id="verify-src" class="live-input" rows="6"></textarea></label>
            <label>公钥：<textarea  id="verify-pub" class="live-input" rows="6"></textarea></label>
            <label>签名：<textarea  id="verify-sign" class="live-input" rows="10"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="verify-hex-sign-btn">验证16进制签名</a>
            <a href="javascript:void(0);" class="button small" id="verify-base-sign-btn">验证base64签名</a>
    </div>
    <div>
            <a name="F6"></a><h3>16进制与base64互转</h3>
            <label>待转换的串：<textarea   rows="6" class="live-input" id="convert-input"></textarea></label>
            <a href="javascript:void(0);" class="button small" id="convert-hex-btn">16进制转换base64</a>
            <a href="javascript:void(0);" class="button small" id="convert-base-btn">base64转换16进制</a>
            <label>转换结果：<textarea  id="convert-output" rows="6"></textarea></label>
    </div>
    <div>
            <a name="F7"></a><h3>身份证校验</h3>
            <label>待校验的身份证：<input type="text" id="identity-input"/></label>
            <a href="javascript:void(0);" class="button small" id="verify-identity-btn">校验</a>
            <h4>身份证样例</h4>
            <%@ include file="/jsp/mock/id_card.jsp"%>
    </div>
        </div>
    </div>
    <div class="footer">
        powered by jintianfan@gmail.com
    </div>
    <div style="display:none">
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256966849'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1256966849' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
