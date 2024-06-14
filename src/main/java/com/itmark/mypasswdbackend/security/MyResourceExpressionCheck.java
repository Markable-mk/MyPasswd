package com.itmark.mypasswdbackend.security;

import com.itmark.mypasswdbackend.entity.sso.MyLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 我资源表达式权限检查 @PreAuthorize("@mrx.hasAuthority('test/articleDateArchive')")
 *
 * @author xiaoma
 * @date 2023/05/14
 */
@Slf4j
@Component(value = "mrx")
public class MyResourceExpressionCheck {
    /**
     * 有权威
     *
     * @param resourceUri 这些uri
     * @return boolean
     */
    public boolean hasAuthority(String resourceUri){
        log.info(resourceUri);
        if (StringUtils.isNoneEmpty(resourceUri)){
            // 这里写对路径接口的权限校验规则
            MyLoginUser loginUser = (MyLoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> permitMenuList = loginUser.getUser().getPermitMenuList();
            if (!Objects.isNull(permitMenuList)&&permitMenuList.contains(resourceUri)){
                return true;
            }
            return false;
        }
        return false;
    }
}
