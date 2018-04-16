package com.lingling.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 鏄惁鎴愬姛
     */
    private boolean success;

    /**
     * service杩斿洖鐨勫璞�
     */
    private Map<String, Object> result = new HashMap<String, Object>();

    /**
     * 榛樿鐨刱ey
     */
    public static final String DEFAULT_MODEL_KEY = "value";

    /**
     * 褰撳墠鐨刱ey
     */
    private String modelKey = DEFAULT_MODEL_KEY;

    /**
     * 杩斿洖鐮�
     */
    private String resultCode;
    private String[] resultCodeParams;
    private String errorMessage;
    private String successMessage;

    /**
     * 甯︽槸鍚︽垚鍔熺殑鏋勯�犳柟娉�
     *
     * @param success
     */
    public Result(boolean success) {
        this.success = success;
    }

    /**
     * 榛樿鏋勯�犳柟娉�
     */
    public Result() {
    }

    /**
     * 鏂板涓�涓繑鍥炵粨鏋�
     *
     * @param obj
     * @return
     */
    public Object addDefaultModel(Object obj) {
        return result.put(DEFAULT_MODEL_KEY, obj);
    }

    /**
     * 鏂板涓�涓甫key鐨勮繑鍥炵粨鏋�
     *
     * @param key
     * @param obj
     * @return
     */
    public Object addDefaultModel(String key, Object obj) {
        modelKey = key;
        return result.put(key, obj);
    }

    /**
     * 鍙栧嚭鎵�鏈夌殑key
     *
     * @return
     */
    public Set<String> keySet() {
        return result.keySet();
    }

    /**
     * 鍙栧嚭鏁翠釜map瀵硅薄
     *
     * @return
     */
    public Map<String, Object> getMap() {
        return result;
    }

    /**
     * 鍙栧嚭榛樿鐨勫��
     *
     * @return
     */
    public Object get() {
        return result.get(modelKey);
    }

    /**
     * 鍙栧嚭鍊�
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return result.get(key);
    }

    /**
     * 鍙栧嚭鍊奸泦鍚�
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Collection values() {
        return result.values();
    }

    /**
     * 杩斿洖鏄惁鎴愬姛
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 璁剧疆杩斿洖鏄惁鎴愬姛
     *
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultCode(String resultCode, String... args) {
        this.resultCode = resultCode;
        this.resultCodeParams = args;
    }

    public String[] getResultCodeParams() {
        return resultCodeParams;
    }

    public void setResultCodeParams(String[] resultCodeParams) {
        this.resultCodeParams = resultCodeParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
