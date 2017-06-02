<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>学生社团</title>
    <style type="text/css">
    .spinner {
        margin: 100px auto;
        width: 90px;
        height: 90px;
        position: relative;
        top: 150px;
        text-align: center;
        -webkit-animation: rotate 2.0s infinite linear;
        animation: rotate 2.0s infinite linear;
    }

    .dot1,
    .dot2 {
        width: 60%;
        height: 60%;
        display: inline-block;
        position: absolute;
        top: 0;
        background-color: rgb(52, 168, 83);
        border-radius: 100%;
        -webkit-animation: bounce 2.0s infinite ease-in-out;
        animation: bounce 2.0s infinite ease-in-out;
    }

    .dot2 {
        top: auto;
        bottom: 0px;
        -webkit-animation-delay: -1.0s;
        animation-delay: -1.0s;
    }

    @-webkit-keyframes rotate {
        100% {
            -webkit-transform: rotate(360deg)
        }
    }

    @keyframes rotate {
        100% {
            transform: rotate(360deg);
            -webkit-transform: rotate(360deg)
        }
    }

    @-webkit-keyframes bounce {
        0%,
        100% {
            -webkit-transform: scale(0.0)
        }
        50% {
            -webkit-transform: scale(1.0)
        }
    }

    @keyframes bounce {
        0%,
        100% {
            transform: scale(0.0);
            -webkit-transform: scale(0.0);
        }
        50% {
            transform: scale(1.0);
            -webkit-transform: scale(1.0);
        }
    }
    </style>
</head>

<body>
    <div class="loading" style="display:block; width:100%; height:100%; position:fixed; left:0px; top:0px; z-index:9999; background-color: #fff">
        <div class="spinner">
            <div class="dot1"></div>
            <div class="dot2"></div>
        </div>
    </div>
    <div class="my-header"></div>
    <div class="my-menu">
        <div class="space"></div>
        <div class="my-item my-item-1">
            <div class="my-item-img"></div>
            <div class="my-item-title my-item-title-animate">足球社</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-2">
            <div class="my-item-img"></div>
            <div class="my-item-title">篮球社</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-3">
            <div class="my-item-img"></div>
            <div class="my-item-title my-item-title-animate">羽毛球社</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-4">
            <div class="my-item-img"></div>
            <div class="my-item-title">乒乓球社</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-3">
            <div class="my-item-img"></div>
            <div class="my-item-title my-item-title-animate">冰球社</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-1">
            <div class="my-item-img"></div>
            <div class="my-item-title my-item-title-animate">游泳俱乐部</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-2">
            <div class="my-item-img"></div>
            <div class="my-item-title">钢琴班</div>
        </div>
        <div class="space"></div>
        <div class="my-item my-item-3">
            <div class="my-item-img"></div>
            <div class="my-item-title my-item-title-animate">绘画班</div>
        </div>
        <div class="space"></div>
    </div>
    <script src="../dist/common.js"></script>
    <script src="../dist/team.js"></script>
</body>

</html>
