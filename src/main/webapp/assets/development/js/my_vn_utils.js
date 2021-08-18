function utfToNormal(content) {
	var content_chars = content.split("");
	content = "";
	var i;
	for (i = 0; i < content_chars.length; i++) {
		var c = content_chars[i];
		switch (c) {
			case 'á':
				{
					c = 'a';
					break;
				}
			case 'à':
				{
					c = 'a';
					break;
				}
			case 'ả':
				{
					c = 'a';
					break;
				}
			case 'ã':
				{
					c = 'a';
					break;
				}
			case 'ạ':
				{
					c = 'a';
					break;
				}
			case 'ă':
				{
					c = 'a';
					break;
				}
			case 'ắ':
				{
					c = 'a';
					break;
				}
			case 'ằ':
				{
					c = 'a';
					break;
				}
			case 'ẳ':
				{
					c = 'a';
					break;
				}
			case 'ẵ':
				{
					c = 'a';
					break;
				}
			case 'ặ':
				{
					c = 'a';
					break;
				}
			case 'â':
				{
					c = 'a';
					break;
				}
			case 'ấ':
				{
					c = 'a';
					break;
				}
			case 'ầ':
				{
					c = 'a';
					break;
				}
			case 'ẩ':
				{
					c = 'a';
					break;
				}
			case 'ẫ':
				{
					c = 'a';
					break;
				}
			case 'ậ':
				{
					c = 'a';
					break;
				}
			case 'é':
				{
					c = 'e';
					break;
				}
			case 'è':
				{
					c = 'e';
					break;
				}
			case 'ẻ':
				{
					c = 'e';
					break;
				}
			case 'ẽ':
				{
					c = 'e';
					break;
				}
			case 'ẹ':
				{
					c = 'e';
					break;
				}
			case 'ê':
				{
					c = 'e';
					break;
				}
			case 'ế':
				{
					c = 'e';
					break;
				}
			case 'ề':
				{
					c = 'e';
					break;
				}
			case 'ể':
				{
					c = 'e';
					break;
				}
			case 'ễ':
				{
					c = 'e';
					break;
				}
			case 'ệ':
				{
					c = 'e';
					break;
				}
			case 'í':
				{
					c = 'i';
					break;
				}
			case 'ì':
				{
					c = 'i';
					break;
				}
			case 'ỉ':
				{
					c = 'i';
					break;
				}
			case 'ĩ':
				{
					c = 'i';
					break;
				}
			case 'ị':
				{
					c = 'i';
					break;
				}
			case 'ó':
				{
					c = 'o';
					break;
				}
			case 'ò':
				{
					c = 'o';
					break;
				}
			case 'ỏ':
				{
					c = 'o';
					break;
				}
			case 'õ':
				{
					c = 'o';
					break;
				}
			case 'ọ':
				{
					c = 'o';
					break;
				}
			case 'ô':
				{
					c = 'o';
					break;
				}
			case 'ố':
				{
					c = 'o';
					break;
				}
			case 'ồ':
				{
					c = 'o';
					break;
				}
			case 'ổ':
				{
					c = 'o';
					break;
				}
			case 'ỗ':
				{
					c = 'o';
					break;
				}
			case 'ộ':
				{
					c = 'o';
					break;
				}
			case 'ơ':
				{
					c = 'o';
					break;
				}
			case 'ớ':
				{
					c = 'o';
					break;
				}
			case 'ờ':
				{
					c = 'o';
					break;
				}
			case 'ở':
				{
					c = 'o';
					break;
				}
			case 'ỡ':
				{
					c = 'o';
					break;
				}
			case 'ợ':
				{
					c = 'o';
					break;
				}
			case 'ú':
				{
					c = 'u';
					break;
				}
			case 'ù':
				{
					c = 'u';
					break;
				}
			case 'ủ':
				{
					c = 'u';
					break;
				}
			case 'ũ':
				{
					c = 'u';
					break;
				}
			case 'ụ':
				{
					c = 'u';
					break;
				}
			case 'ư':
				{
					c = 'u';
					break;
				}
			case 'ứ':
				{
					c = 'u';
					break;
				}
			case 'ừ':
				{
					c = 'u';
					break;
				}
			case 'ử':
				{
					c = 'u';
					break;
				}
			case 'ữ':
				{
					c = 'u';
					break;
				}
			case 'ự':
				{
					c = 'u';
					break;
				}
			case 'Á':
				{
					c = 'A';
					break;
				}
			case 'À':
				{
					c = 'A';
					break;
				}
			case 'Ả':
				{
					c = 'A';
					break;
				}
			case 'Ã':
				{
					c = 'A';
					break;
				}
			case 'Ạ':
				{
					c = 'A';
					break;
				}
			case 'Ă':
				{
					c = 'A';
					break;
				}
			case 'Ắ':
				{
					c = 'A';
					break;
				}
			case 'Ằ':
				{
					c = 'A';
					break;
				}
			case 'Ẳ':
				{
					c = 'A';
					break;
				}
			case 'Ẵ':
				{
					c = 'A';
					break;
				}
			case 'Ặ':
				{
					c = 'A';
					break;
				}
			case 'Â':
				{
					c = 'A';
					break;
				}
			case 'Ấ':
				{
					c = 'A';
					break;
				}
			case 'Ầ':
				{
					c = 'A';
					break;
				}
			case 'Ẩ':
				{
					c = 'A';
					break;
				}
			case 'Ẫ':
				{
					c = 'A';
					break;
				}
			case 'Ậ':
				{
					c = 'A';
					break;
				}
			case 'É':
				{
					c = 'E';
					break;
				}
			case 'È':
				{
					c = 'E';
					break;
				}
			case 'Ẻ':
				{
					c = 'E';
					break;
				}
			case 'Ẽ':
				{
					c = 'E';
					break;
				}
			case 'Ẹ':
				{
					c = 'E';
					break;
				}
			case 'Ê':
				{
					c = 'E';
					break;
				}
			case 'Ế':
				{
					c = 'E';
					break;
				}
			case 'Ề':
				{
					c = 'E';
					break;
				}
			case 'Ể':
				{
					c = 'E';
					break;
				}
			case 'Ễ':
				{
					c = 'E';
					break;
				}
			case 'Ệ':
				{
					c = 'E';
					break;
				}
			case 'Í':
				{
					c = 'I';
					break;
				}
			case 'Ì':
				{
					c = 'I';
					break;
				}
			case 'Ỉ':
				{
					c = 'I';
					break;
				}
			case 'Ĩ':
				{
					c = 'I';
					break;
				}
			case 'Ị':
				{
					c = 'I';
					break;
				}
			case 'Ó':
				{
					c = 'O';
					break;
				}
			case 'Ò':
				{
					c = 'O';
					break;
				}
			case 'Ỏ':
				{
					c = 'O';
					break;
				}
			case 'Õ':
				{
					c = 'O';
					break;
				}
			case 'Ọ':
				{
					c = 'O';
					break;
				}
			case 'Ô':
				{
					c = 'O';
					break;
				}
			case 'Ố':
				{
					c = 'O';
					break;
				}
			case 'Ồ':
				{
					c = 'O';
					break;
				}
			case 'Ổ':
				{
					c = 'O';
					break;
				}
			case 'Ỗ':
				{
					c = 'O';
					break;
				}
			case 'Ộ':
				{
					c = 'O';
					break;
				}
			case 'Ơ':
				{
					c = 'O';
					break;
				}
			case 'Ớ':
				{
					c = 'O';
					break;
				}
			case 'Ờ':
				{
					c = 'O';
					break;
				}
			case 'Ở':
				{
					c = 'O';
					break;
				}
			case 'Ỡ':
				{
					c = 'O';
					break;
				}
			case 'Ợ':
				{
					c = 'O';
					break;
				}
			case 'Ú':
				{
					c = 'U';
					break;
				}
			case 'Ù':
				{
					c = 'U';
					break;
				}
			case 'Ủ':
				{
					c = 'U';
					break;
				}
			case 'Ũ':
				{
					c = 'U';
					break;
				}
			case 'Ụ':
				{
					c = 'U';
					break;
				}
			case 'Ư':
				{
					c = 'U';
					break;
				}
			case 'Ứ':
				{
					c = 'U';
					break;
				}
			case 'Ừ':
				{
					c = 'U';
					break;
				}
			case 'Ử':
				{
					c = 'U';
					break;
				}
			case 'Ữ':
				{
					c = 'U';
					break;
				}
			case 'Ự':
				{
					c = 'U';
					break;
				}
			default:
				{
					break;
				}
		}
		content = content.concat(c);
	}
	return content;
}

function utfToNormal2(content) {
	var vietnamese_chars = ["á", "à", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â", "ấ", "ầ", "ẩ", "ẫ", "ậ", "é", "è", "ẻ", "ẽ", "ẹ", "ê", "ế", "ề", "ể", "ễ", "ệ", "í", "ì", "ỉ", "ĩ", "ị", "ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ", "ơ", "ớ", "ờ", "ở", "ỡ", "ợ", "ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự"];
	var vietnamese_upper_chars = [];
	var common_chars = ["a", "e", "i", "o", "u"];
	var common_upper_chars = ["A", "E", "I", "O", "U"];

	var q;
	for (q = 0; q < vietnamese_chars.length; q++) {
		vietnamese_upper_chars[q] = vietnamese_chars[q].toUpperCase();
	}

	var content_chars = content.split("");
	content = "";
	var i;
	for (i = 0; i < content_chars.length; i++) {
		var j;
		for (j = 0; j < vietnamese_chars.length; j++) {
			if (content_chars[i] === vietnamese_chars[j]) {
				if (j < 17) {
					content_chars[i] = common_chars[0];
				} else if (j < 28) {
					content_chars[i] = common_chars[1];
				} else if (j < 33) {
					content_chars[i] = common_chars[2];
				} else if (j < 50) {
					content_chars[i] = common_chars[3];
				} else {
					content_chars[i] = common_chars[4];
				}
				break;
			} else if (content_chars[i] === vietnamese_upper_chars[j]) {
				if (j < 17) {
					content_chars[i] = common_upper_chars[0];
				} else if (j < 28) {
					content_chars[i] = common_upper_chars[1];
				} else if (j < 33) {
					content_chars[i] = common_upper_chars[2];
				} else if (j < 50) {
					content_chars[i] = common_upper_chars[3];
				} else {
					content_chars[i] = common_upper_chars[4];
				}
				break;
			}
		}
		content = content.concat(content_chars[i]);
	}
	return content;
}









