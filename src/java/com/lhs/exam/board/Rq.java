package com.lhs.exam.board;

import java.util.Map;

public class Rq {
    private String url;
    private String urlPath;
    private Map<String, String> params;

    // 필드추가가능

    // 수정가능
    Rq(String url) {
        this.url = url;
        urlPath = Util.getUrlPathFromUrl(this.url);
        params = Util.getParamsFromUrl(this.url);
    }

    // 수정가능, if문 금지
    public Map<String, String> getParams() {
        return params;
    }
    public int getIntParam(String paramName, int defaultValue) {
        if (params.containsKey(paramName)==false ){

            return defaultValue;
        }else{
            try {
                return Integer.parseInt(params.get(paramName));
            }catch (NumberFormatException e){
                return defaultValue;
            }
        }
    }
    public String getParam(String paramName, String defaultValue) {
        if (params.containsKey(paramName)==false ){
            return defaultValue;
        }else{
            return params.get(paramName);
        }
    }

    // 수정가능, if문 금지
    public String getUrlPath() {
        return urlPath;
    }
}
