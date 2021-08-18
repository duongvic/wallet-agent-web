var cachedMap = {};
var currentServiceCode;
var currentCardCode;

function loadPhoneCommission(phoneNumber, selectTag, title, selectValue, csrfName, csrfToken) {
	var serviceCode = phoneNumber.substr(0, 3);
	if (serviceCode !== currentServiceCode) {
		currentServiceCode = serviceCode;
		loadDataCommission('top_up', '', phoneNumber, currentServiceCode,
				selectTag, csrfName, csrfToken, title, selectValue);
	}
}

function loadTopUpCommission(cardName, selectTag, title, selectValue, csrfName, csrfToken) {
  if (cardName !== currentCardCode) {
    currentCardCode = cardName;
    loadDataCommission('top_up', cardName, '', currentCardCode,
        selectTag, csrfName, csrfToken, title, selectValue);
  }
}

function loadCardCommission(cardName, selectTag, title, selectValue, csrfName, csrfToken) {
	if (cardName !== currentCardCode) {
		currentCardCode = cardName;
		loadDataCommission('pin_card', cardName, '', currentCardCode,
				selectTag, csrfName, csrfToken, title, selectValue);
	}
}

function loadGameCardCommission(cardName, selectTag, title, selectValue, csrfName, csrfToken) {
    if (cardName !== currentCardCode) {
        currentCardCode = cardName;
        loadDataCommission('game_card', cardName, '', currentCardCode,
            selectTag, csrfName, csrfToken, title, selectValue);
    }
}

function loadDataCardCommission(cardName, selectTag, title, selectValue, csrfName, csrfToken) {
  if (cardName !== currentCardCode) {
    currentCardCode = cardName;
    loadDataCommission('data_card', cardName, '', currentCardCode,
        selectTag, csrfName, csrfToken, title, selectValue);
  }
}

function loadDataCommission(commissionType, cardName, phoneNumber, cacheKey,
		selectTag, csrfName, csrfToken, title, selectValue) {
	if (cachedMap[cacheKey] !== undefined) {
		loadCommissionToModel(commissionType, cachedMap[cacheKey], selectTag, title, selectValue);
	} else {
		$.ajax({
			type : "GET",
			contentType : "application/json;charset=utf-8",
			url : "/ajax-controller/get-card-commission",
			beforeSend : function(xhr) {
				if (csrfName && csrfToken) {
					xhr.setRequestHeader(csrfName, csrfToken);
				}
			},
			data : {
        serviceTypeId: commissionType,
				cardName : cardName,
				phoneNumber : phoneNumber
			},
			dataType : 'json',
			timeout : 60000,
			success : function(data) {
				cachedMap[cacheKey] = data;
				loadCommissionToModel(commissionType, data, selectTag, title, selectValue);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
}

function loadCommissionToModel(commissionType, data, selectTag, title, selectValue) {
  var arrCapacity = [];
  var prices = data.prices;
  if (data.serviceDesc == 'Mua thẻ data Vinaphone') {
    for (i in prices) {
      if (prices[i] == "20000") {
        arrCapacity.push('1.2GB')
      }
      if (prices[i] == "50000") {
        arrCapacity.push('3GB')
      }
      if (prices[i] == "100000") {
        arrCapacity.push('8GB')
      }
    }
  } else if (data.serviceDesc == 'Mua thẻ data MobiFone') {
    for (i in prices) {
      if (prices[i] == "10000") {
        arrCapacity.push('1GB')
      }
      else if (prices[i] == "20000") {
        arrCapacity.push('1.5GB')
      }
      else if (prices[i] == "50000") {
        arrCapacity.push('2.5GB')
      }
      else if (prices[i] == "200000") {
        arrCapacity.push('5.5GB')
      }
      else if (prices[i] == "500000") {
        arrCapacity.push('32GB')
      }
    }
  } else if (data.serviceDesc == 'Mã thẻ data Viettel') {
    for (i in prices) {
      if (prices[i] == "40000") {
        arrCapacity.push('1GB')
      }
      else if (prices[i] == "70000") {
        arrCapacity.push('3GB')
      }
      else if (prices[i] == "90000") {
        arrCapacity.push('5GB')
      }
      else if (prices[i] == "125000") {
        arrCapacity.push('8GB')
      }
      else if (prices[i] == "200000") {
        arrCapacity.push('15GB')
      }
    }
  }

  if (selectTag !== null && selectTag !== undefined && selectTag !== '') {
    selectTag.empty();
    if ('' !== title) {
      if (commissionType == 'data_card') {
        selectTag.append("<option value=\"" + prices[i] + "\" >"
          + formatCurrency(prices[i]) +  "__" + (arrCapacity[i]) + "</option>");
      }
      else {
        selectTag.append("<option value=\"" + prices[i] + "\" >"
          + formatCurrency(prices[i]) + "</option>");
      }
    }
    if (prices != '') {
      if (commissionType == 'data_card' && arrCapacity != undefined) {
        for (i in prices) {
          selectTag.append("<option value=\"" + prices[i] + "\" "
            + (prices[i] === selectValue ? "selected" : "") + ">"
            + formatCurrency(prices[i]) +  "__" + (arrCapacity[i]) + "</option>");
        }
      }
      else {
        for (i in prices) {
          selectTag.append("<option value=\"" + prices[i] + "\" "
            + (prices[i] === selectValue ? "selected" : "") + ">"
            + formatCurrency(prices[i]) + "</option>");
        }
      }
    }
  }
  onLoadCommissionToModel(commissionType, data, data.commissionFee, prices);
}
