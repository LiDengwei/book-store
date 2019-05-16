package com.book.manager.security.impl;

import com.book.manager.api.service.AuthorityService;
import com.book.manager.api.service.ResourceService;
import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.RoleEntity;
import com.book.manager.security.service.CustomInvocationSecurityMetadataSource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/** 
 * TODO(这里用一句话描述这个类的职责).
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * @ClassName: CustomInvocationSecurityMetadataSourceImpl
 * @author Johnny_L_Q
 */
@Service("customInvocationSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSourceImpl implements CustomInvocationSecurityMetadataSource {

    @Resource
    private ResourceService resourceService;
    
    @Resource
    private AuthorityService authorityService;

    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    public  CustomInvocationSecurityMetadataSourceImpl(){}

    public  CustomInvocationSecurityMetadataSourceImpl(AuthorityService authorityService,ResourceService resourceService){
        this.resourceService = resourceService ;
        this.authorityService = authorityService ;
    }

    private static HashMap<String, Collection<ConfigAttribute>> resourceMap = null;

    static {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    }

    //private AntPathRequestMatcher pathMatcher;

//    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;
    /**
     * 自定义方法，这个类放入到Spring容器后， 
     * 指定init为初始化方法，从数据库中读取资源 
     * TODO(这里用一句话描述这个方法的作用).
     */
    @PostConstruct
    public void init() {
        loadResourceDefine();
    }

    /**
     * 
     * TODO(程序启动的时候就加载所有资源信息).
     */
    private HashMap<String,Collection<ConfigAttribute>> loadResourceDefine() {

        // 在Web服务器启动时，提取系统中的所有权限。
        //sql = "select authority_name from pub_authorities";

            List<AuthorityEntity> query = authorityService.getAllAuthoritys();

        /**//*
             * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
             * sparta
             */
            resourceMap = getInstance() ;
            for (AuthorityEntity auth : query) {
                String authName = auth.getAuthorityName();

                ConfigAttribute ca = new SecurityConfig(auth.getAuthorityName());

                List<ResourceEntity> resources = resourceService.getResourcesByAuthName(authName);


                for (ResourceEntity resource : resources) {
                    //String authName = auth2.getAuthorityName();
                    String url = resource.getResourceUrl();

                /**//*
                     * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                     * sparta
                     */
                    if (resourceMap.containsKey(url)) {

                        Collection<ConfigAttribute> value = resourceMap.get(url);
                        value.add(ca);
                        resourceMap.put(url, value);
                    } else {
                        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                        atts.add(ca);
                        resourceMap.put(url, atts);
                    }

                }

            }
        System.out.println("resourceMap" + resourceMap);
        return resourceMap ;

    }

    /**
     * TODO(自定义方法，将List<Role>集合转换为框架需要的Collection<ConfigAttribute>集合).
     * @param roles 角色集合
     * @return list 封装好的Collection集合
     */
    private Collection<ConfigAttribute> listToCollection(List<RoleEntity> roles) {
        List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();

        for (RoleEntity role : roles) {
            list.add(new SecurityConfig(role.getRoleName()));

        }
        return list;
    }

    /*
     * <p>Title: getAllConfigAttributes</p>
     * <p>Description: </p>
     * @return
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /*
     * 根据URL，找到相关的权限配置
     * <p>Title: getAttributes</p>
     * <p>Description: </p>
     * @param arg0
     * @return
     * @throws IllegalArgumentException
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        //object 是一个URL ,为用户请求URL
        String url = ((FilterInvocation)object).getRequestUrl();
//        HashMap<String, Collection<ConfigAttribute>> resourceMap = null;
        resourceMap = getInstance();
       if("/".equals(url)){
           return null;
       }
        int firstQuestionMarkIndex = url.indexOf("?");
        //判断请求是否带有参数 如果有参数就去掉后面的参数
        if(firstQuestionMarkIndex != -1){
            url = url.substring(0,firstQuestionMarkIndex);
        }
        
        Iterator<String> ite = resourceMap.keySet().iterator();
        //取到请求的URL后与上面取出来的资源做比较
        while (ite.hasNext()) {
            String resURL = ite.next();
            if(urlMatcher.pathMatchesUrl(url, resURL)){
                return resourceMap.get(resURL);
            }
        }
        return null;
    }


    /*
     * <p>Title: supports</p>
     * <p>Description: </p>
     * @param arg0
     * @return
     * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * 设置 resourceMap 为单例模式，保证每次更新resourceMap后
     * 每个实例拿到的 resourceMap 为最新的数据
     */
    private static HashMap<String, Collection<ConfigAttribute>> getInstance(){
        if(resourceMap == null){
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            return  resourceMap ;
        }
        else{
            return resourceMap ;
        }
    }

}
