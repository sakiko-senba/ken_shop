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
<link rel="stylesheet" href="css/top.css">
<title>ショッピングサイトLatteトップページ</title>
</head>
<body id="index_page">
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
		<main id="main_contents">
			<section>
				<h3>欲しいモノのカテゴリー選択してください</h3>
				<form action="kenshop" method="post">
					<table>
						<tr>
							<td>分類</td>
							<td><select name="genre">
									<option value="book" selected="selected">書籍</option>
									<option value="cd">CD</option>
									<option value="game">GAME</option>
							</select></td>
							<td>キーワード</td>
							<td><input type="text" name="keyword" size="20"></td>
							<td><input type="submit" name="search" value="検索">
								<input type="hidden" name="act" value="search"></td>
						</tr>
					</table>
				</form>
			</section>
			<section>
				<h3>現在選択されている商品</h3>
				<table class="selected_item">
					<tr>
						<td>選択商品 <c:out value="${sessionScope.cart.size()}" default="0" /> 個
						</td>
						<td>
							<form action="kenshop" method="post">
								<input type="image" src="img/btn_cart.gif" alt="カートの中身を見る">
								<input type="hidden" name="act" value="check">
							</form>
						</td>
					</tr>
				</table>
			</section>
			<section>
				<h3>選択カテゴリー商品</h3>
				<table class="selected_category">
					<tr>
						<td>商品</td>
						<td>商品名 著者 価格</td>
						<td></td>
					</tr>
				</table>
				<c:if test="${not empty sessionScope.table_items }">
						<c:forEach var="item" items="${sessionScope.table_items}">
						<form action="kenshop" method="post">
							<div class="items">
									<div class="lineup_img"><img alt="${item.getItemName()}" src="${item.getItemImage()}"></div>
								<div class="lineup">
									<c:out value="${item.getItemName()}"/>&nbsp;&nbsp;
									<c:out value="${item.getItemArtist()}"/>&nbsp;&nbsp;
									<c:out value="${item.getItemPrice()}"/>
								</div>
								<div class="lineup_btn">
									<input type="image" src="img/btn_addition.gif" alt="カートに追加">
									<input type="hidden" name="act" value="add">
									<input type="hidden" name="id" value="${item.getItemID()}">
									<input type="hidden" name="name" value="${item.getItemName()}">
									<input type="hidden" name="artist" value="${item.getItemArtist()}">
									<input type="hidden" name="price" value="${item.getItemPrice()}">
								</div>
							</div>
						</form>
					</c:forEach>
				</c:if>
				<c:if test="${request.no_item == \"\"}">
					<p class="err_message"><c:out value="${request.message}"/></p>
				</c:if>
			</section>
		</main>
		<!-- InstanceEndEditable -->
		<hr>
		<div id="sub_contents">
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
				全国無料配送<br> 各種クレジットカードもご利用になれます。
			</p>
			<p>販売元：株式会社シンクスバンク</p>
		</div>
		<hr>
		<footer id="footer">
			<p>
				<a href="#wrap"><img src="img/page_top.gif" width="49" height="9" alt="ページトップ"> </a>
			</p>
			<address>&copy; 2012 Thinketh Bank Co., Ltd. All Rights Reserved.</address>
		</footer>
	</div>
</body>
</html>