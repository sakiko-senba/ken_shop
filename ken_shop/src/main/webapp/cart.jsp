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
<title>ショッピングサイトLatteトップページ</title>
</head>
<body id="mycart_page">
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
				<img src="img/yokohama01.jpg" alt="Latteトップページ" width="900" height="200">
			</h2>
		</div>
		<!-- InstanceBeginEditable name="main_contents" -->
		<main id="main_contents">
			<h3>買い物カゴの中身</h3>
			<p>↓↓現在買い物カゴには以下の商品が入っています。↓↓</p>
			<table>
				<tr>
					<th class="cart">タイトル</th>
					<th class="cart">メーカー等</th>
					<th class="cart">価格</th>
					<th class="cart">&nbsp;</th>
				</tr>
			</table>
			<!-- ●！！！！表の作成はここから！！！！●  -->
			<c:forEach var="item" items="${sessionScope.cart}" varStatus="status">
				<form action="kenshop" method="post">
					<div class="items">
						<div class="lineup"><c:out value="${item.getItemName()}"></c:out></div>
						<div class="lineup"><c:out value="${item.getItemArtist()}"></c:out></div>
						<div class="lineup"><c:out value="${item.getItemPrice() }"></c:out></div>
						<div class="lineup_btn">
							<input type="submit" value="取り消し">
							<input type="hidden" name="act" value="remove">
							<input type="hidden" name="remove" value="${status.index}">
						</div>
					</div>
				</form>
			</c:forEach>
			<!-- ↑↑↑↑表の作成はここまで↑↑↑↑  -->
			<form action="kenshop" method="post">
				<p>
					↓↓上記の内容で注文画面へ進む場合はこちら↓↓<br>
					<input type="image"	src="img/btn_confirmation.gif" alt="戻る">
				</p>
				<input type="hidden" name="act" value="for_order">
			</form>
			<p>
				↓↓まだ買い物を続けたいので検索画面に戻る場合はこちら↓↓<br> <a href="kenshop">検索画面に戻る</a>
			</p>
		</main>
		<!-- InstanceEndEditable -->
		<hr>
		<div id="sub_contents">
			<p>
				<img src="img/short_img.jpg" width="146" height="112" alt="short">
			</p>
			<p>
				<a href="#"><img src="img/btn_cart.gif" width="146" height="20"	alt="カートの中身をみる"> </a>
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
			<address>&copy; 2012 Thinketh Bank Co., Ltd. All Rights
				Reserved.</address>
		</footer>
	</div>
</body>
</html>