package com.koutao.worker.service;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import com.koutao.account.bean.AccountDto;
import com.koutao.account.bean.GenericAccountResponse;
import com.koutao.account.service.AccountService;
import com.koutao.common.auth.AuthConstant;
import com.koutao.common.config.AppProps;
import com.koutao.common.crypto.Hash;
import com.koutao.common.error.ServiceException;
import com.koutao.company.bean.AdminOfList;
import com.koutao.company.bean.GetAdminOfResponse;
import com.koutao.company.bean.WorkerOfList;
import com.koutao.company.service.AdminService;
import com.koutao.company.service.CompanyService;
import com.koutao.company.service.WorkerService;
import com.koutao.worker.bean.GetWorkerOfResponse;
import com.koutao.worker.bean.IAmDto;
import com.koutao.worker.bean.IntercomSettingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WhoAmIService {

    static final ILogger logger = SLoggerFactory.getLogger(WhoAmIService.class);

    @Autowired
    CompanyService companyService;
    @Autowired
    WorkerService workerService;
    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    /*@Autowired
    SentryClient sentryClient;*/

    @Autowired
    AppProps appProps;

    public IAmDto findWhoIAm(String userId) {
        IAmDto iAmDto = IAmDto.builder()
                .userId(userId)
                .build();

        GetWorkerOfResponse workerOfResponse = null;
        try {
            workerOfResponse = new GetWorkerOfResponse(workerService.getWorkerOf(userId));
        } catch (Exception ex) {
            String errMsg = "unable to get worker of list";
            handleErrorAndThrowException(ex, errMsg);
        }
        if (!workerOfResponse.isSuccess()) {
            handleErrorAndThrowException(workerOfResponse.getMessage());
        }
        WorkerOfList workerOfList = workerOfResponse.getWorkerOfList();
        iAmDto.setWorkerOfList(workerOfList);

        GetAdminOfResponse getAdminOfResponse = null;
        try {
            getAdminOfResponse = new GetAdminOfResponse(adminService.getAdminOf(userId));
        } catch (Exception ex) {
            String errMsg = "unable to get admin of list";
            handleErrorAndThrowException(ex, errMsg);
        }

        if (!getAdminOfResponse.isSuccess()) {
            handleErrorAndThrowException(getAdminOfResponse.getMessage());
        }
        AdminOfList adminOfList = getAdminOfResponse.getAdminOfList();

        iAmDto.setAdminOfList(adminOfList);

        return iAmDto;
    }

    public IntercomSettingsDto findIntercomSettings(String userId) {
        IntercomSettingsDto intercomSettingsDto = IntercomSettingsDto.builder()
                .appId(appProps.getIntercomAppId())
                .userId(userId)
                .build();


        GenericAccountResponse genericAccountResponse = null;
        try {
            genericAccountResponse = new GenericAccountResponse(accountService.get(userId));
        } catch (Exception ex) {
            String errMsg = "unable to get account";
            handleErrorAndThrowException(ex, errMsg);
        }
        if (!genericAccountResponse.isSuccess()) {
            handleErrorAndThrowException(genericAccountResponse.getMessage());
        }
        AccountDto account = genericAccountResponse.getAccount();

        intercomSettingsDto.setName(account.getName());
        intercomSettingsDto.setEmail(account.getEmail());
        intercomSettingsDto.setCreatedAt(account.getMemberSince());

        try {
            String userHash = Hash.encode(appProps.getIntercomSigningSecret(), userId);
            intercomSettingsDto.setUserHash(userHash);
        } catch (Exception ex) {
            String errMsg = "fail to compute user hash";
            handleErrorAndThrowException(ex, errMsg);
        }

        return intercomSettingsDto;
    }

    void handleErrorAndThrowException(String errMsg) {
        logger.error(errMsg);
        //sentryClient.sendMessage(errMsg);
        throw new ServiceException(errMsg);
    }

    void handleErrorAndThrowException(Exception ex, String errMsg) {
        logger.error(errMsg, ex);
        //sentryClient.sendException(ex);
        throw new ServiceException(errMsg, ex);
    }
}
