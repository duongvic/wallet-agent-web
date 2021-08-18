package vn.mog.ewallet.consumer.web.utils;

import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_CUSTOMER;
import static vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType.ID_MERCHANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.consumer.web.controller.AbstractController;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ICustomerEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.ISystemEndpoint;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.customer.beans.Customer;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankProfileResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindBankResponse;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.FindLocationRequest;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Bank;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.BankProfile;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.CustomerType;
import vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.beans.Location;
import vn.mog.framework.cache.dynacache.PassiveDynaCache;
import vn.mog.framework.cache.dynacache.common.CacheConstants;


@Service
public class CacheDataUtil implements InitializingBean {

  private static final String CACHED_CUSTOMERS_BY_BIZ = "all_customer_cached_key";

  private static final String CACHED_CITIES = "all_citie_cached_key";
  private static final String CACHED_DISTRICTS = "all_district_cached_key";
  private static final String CACHED_COMMUES = "all_commue_cached_key";

  private static final String CACHED_BANKS = "all_bank_cached_key";
  private static final String CACHED_BANK_PROFILES = "all_bank_profile_cached_key";


  @Autowired
  protected ICustomerEndpoint customerEndpoint;

  @Autowired
  protected ISystemEndpoint systemEndpoint;

  @Autowired
  private PassiveDynaCache passiveDynaCache;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.customerEndpoint == null) {
      throw new IllegalStateException("ICustomerEndpoint is required");
    }
  }

  public List<Customer> getCustomersByBiz() {
    try {
      Object cacheItem = passiveDynaCache.getCachedItem(CACHED_CUSTOMERS_BY_BIZ);
      if (cacheItem == null) {
        FindFullCustomerRequest fcRequest = new FindFullCustomerRequest();
        fcRequest.setCustomerTypes(Arrays.asList(ID_CUSTOMER, ID_MERCHANT, CustomerType.ID_AGENT));
        List<Customer> customers = customerEndpoint.findCustomers(fcRequest).getCustomers();
        passiveDynaCache.setCachedItem(CACHED_CUSTOMERS_BY_BIZ, customers,
            CacheConstants.MEMCACHED_TIMEOUT_5_MINS);
        return customers;
      }
      return (List<Customer>) cacheItem;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  public List<Bank> getBanks() {
    try {
      Object cacheItem = passiveDynaCache.getCachedItem(CACHED_BANKS);
      if (cacheItem == null) {
        List<Bank> banks = systemEndpoint.findBanks(new FindBankRequest()).getBanks();
        passiveDynaCache
            .setCachedItem(CACHED_BANKS, banks, CacheConstants.MEMCACHED_TIMEOUT_12_HOURS);
        return banks;
      }
      return (List<Bank>) cacheItem;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  public List<BankProfile> getBankProfiles() {
    try {
      Object cacheItem = passiveDynaCache.getCachedItem(CACHED_BANK_PROFILES);
      if (cacheItem == null) {
        List<BankProfile> banks = new ArrayList<>();
        FindBankProfileResponse response = systemEndpoint.getProfileBanks(new FindBankProfileRequest());
        if(response != null && response.getStatus().getCode() ==0 ){
          banks=response.getBankProfiles();
        }
        passiveDynaCache
            .setCachedItem(CACHED_BANK_PROFILES, banks, CacheConstants.MEMCACHED_TIMEOUT_12_HOURS);
        return banks;
      }
      return (List<BankProfile>) cacheItem;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  public Collection<Location> findLocations(Integer locationType) {
    try {
      Object cacheItem = null;
      String cachName = StringUtils.EMPTY;
      switch (locationType) {
        case 1://city
          cacheItem = passiveDynaCache.getCachedItem(CACHED_CITIES);
          cachName = CACHED_CITIES;
          break;
        case 3://COMMUE
          cacheItem = passiveDynaCache.getCachedItem(CACHED_COMMUES);
          cachName = CACHED_COMMUES;
          break;
        case 2://district
          cacheItem = passiveDynaCache.getCachedItem(CACHED_DISTRICTS);
          cachName = CACHED_DISTRICTS;
          break;
      }

      if (cacheItem == null) {
        FindLocationRequest flRequest = new FindLocationRequest();
        Collection<Location> locations = (Collection<Location>) systemEndpoint.findLocations(flRequest);
        passiveDynaCache
            .setCachedItem(cachName, locations, CacheConstants.MEMCACHED_TIMEOUT_5_MINS);
        return locations;
      }
      return (Collection<Location>) cacheItem;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }


}
