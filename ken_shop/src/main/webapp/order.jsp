<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ken.bean.Item"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/cart.css">
<title>ショッピングサイトLatte送付先登録画面</title>
<script>
<!--
	function check() {
		for (i = 0; i < document.forms[0].elements.length; i++) {
			if (document.forms[0].elements[i].value == "") {
				window.alert("入力されていない項目があります。");
				return false;
			}
		}
		return true;
	}
//-->
</script>
</head>
<body id="chumon_page">
	<div id="wrap">
		<header id="header">
			<h1>
				<img src="img/site_id_new.png" width="204" height="120" alt="logo">
			</h1>
		</header>
		<hr>
		<nav id="nav">
			<ul>
				<li><a href="#">ホーム</a></li>
				<li><a href="#">ショップガイド</a></li>
				<li><a href="#">よくある質問</a></li>
				<li><a href="#">会社案内</a></li>
				<li><a href="#">お問い合わせ</a></li>
			</ul>
		</nav>
		<div id="visual">
			<h2>
				<img src="img/yokohama01.jpg" alt="Latteトップページ" width="900"
					height="200">
			</h2>
		</div>
		<!-- InstanceBeginEditable name="main_contents" -->
		<main id="main_contents">
			<h3>送付先登録画面</h3>
			<p>
				買い物の内容は以下になります<br>
			</p>

			<table>
				<tr>
					<th class="cart">タイトル</th>
					<th class="cart">メーカー等</th>
					<th class="cart">価格</th>
				</tr>
				<!-- ●！！！！表の作成はここから！！！！●  -->

				<c:forEach var="item" items="${sessionScope.cart}">
					<tr>
						<td class="cart"><c:out value="${item.getItemName()}"></c:out></td>
						<td class="cart"><c:out value="${item.getItemArtist()}"></c:out></td>
						<td class="cart"><c:out value="${item.getItemPrice()}"></c:out></td>
					</tr>

					<c:set var="total" value="${total + item.itemPrice}"></c:set>
				</c:forEach>
				<!-- ↑↑↑↑表の作成はここまで↑↑↑↑  -->
			</table>
			<br>
			<p>
				<strong>合計金額は税込みで<c:out value="${total}"/>円になります。</strong>
			</p>
			<p>
				下のフォームにお客様のお名前、ご住所、電話番号、メールアドレスを入力して、<br>
				よろしければ「注文する」ボタンを押してください。<br> 項目は必ず入力してください。機種依存文字は入力できません。
			</p>
			<form action="kenshop" method="post" onsubmit="return check();">
				<table class="regist_form">
					<tr>
						<td>お名前</td>
						<td><input type="text" name="name" size="20"><br>
							（例）山田太郎</td>
					</tr>
					<tr>
						<td>フリガナ</td>
						<td><input type="text" name="kana_name" size="20"><br>
							（例）ヤマダタロウ</td>
					</tr>
					<tr>
						<td>郵便番号</td>
						<td><input type="text" name="postcode" size="10" maxlength="8">
						（郵便番号検索は <a	href="http://www.post.japanpost.jp/zipcode/index.html" target="_blank">検索</a>）<br>
						（例）153-0052<br> ※ 国外の方は000-0000 と入力してください。</td>
					</tr>
					<tr>
						<td>住所</td>
						<td><input type="text" name="address" size="55"><br>
							（例）東京都世田谷区北沢1-1-1KENコーポ203号室</td>
					</tr>
					<tr>
						<td>電話番号</td>
						<td><input type="text" name="tel" size="20" maxlength="13"><br> （例）03-0000-0000</td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><input type="text" name="mail" size="55"><br>（例）hogehoge@kenschool.jp</td>
					</tr>
					<tr>
						<td colspan="2" class="order_confirm"><span>↓↓上記の内容で問題なければ、注文を確定いたします↓↓</span><br>
							<input name='cart_enter' type="image"
								src="img/btn_order.gif" alt='注文'
								onmouseover='document.body.style.cursor="hand";'
								onmouseout='document.body.style.cursor="default";'>
							<input type='hidden' name='act' value='order'>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="back"><a href="kenshop?act=check"> ショッピングカートに戻る</a></td>
					</tr>
				</table>
			</form>
		</main>
		<!-- InstanceEndEditable -->
		<hr>
		<div id="sub_contents">
			<p>
				<img src="img/short_img.jpg" width="146" height="112" alt="short">
			</p>
			<p>
				<a href="#"><img src="img/btn_cart.gif" width="146" height="20" alt="カートの中身をみる"></a>
			</p>
			<h4>
				<img src="img/sidemn_shopguide.gif" width="96" height="15" alt="ショッピングガイド">
			</h4>
			<ul>
				<li><a href="#">ご購入方法</a></li>
				<li><a href="#">お支払い方法</a></li>
				<li><a href="#">配送料</a></li>
				<li><a href="#">ラッピング</a></li>
				<li><a href="#">返品・交換</a></li>
			</ul>
			<p>
				全国無料配送<br>各種クレジットカードもご利用になれます。
			</p>
			<p>販売元：株式会社シンクスバンク</p>
		</div>
		<hr>
		<footer id="footer">
			<p>
				<a href="#wrap"><img src="img/page_top.gif" width="49" height="9" alt="ページトップ"></a>
			</p>
			<address>Copyright &copy; 2012-2014 Thinketh Bank Co., Ltd.
				All Rights Reserved.</address>
		</footer>
	</div>
</body>
<!-- InstanceEnd -->
</html>