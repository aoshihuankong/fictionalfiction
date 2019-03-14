package com.huankong.fictionalfiction.controller;

import com.huankong.fictionalfiction.ipproxy.IPProxySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IPProxyController {
    @Autowired
    IPProxySchedule ipProxySchedule;
    @GetMapping(value = "ipproxyrefresh")
    public void ipProxyRefresh() {
        ipProxySchedule.ipProxyInit();
    }
}
