package com.github.bestheroz.standard.common.exception;

import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    // 아래서 놓친 예외가 있을때 이곳으로 확인하기 위해 존재한다.
    // 놓친 예외는 이곳에서 확인하여 추가해주면 된다.
    @org.springframework.web.bind.annotation.ExceptionHandler({Throwable.class})
    public ResponseEntity<ApiResult> exception(final Throwable e) {
        log.debug(ExceptionUtils.getStackTrace(e));
        return Result.error();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BusinessException.class})
    public ResponseEntity<ApiResult> response(final BusinessException e) {
        log.debug(e.toString());
        return ResponseEntity.badRequest().body(e.getApiResult());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ApiResult> bindException(final Throwable e) {
        log.debug(ExceptionUtils.getStackTrace(e));
        return Result.error(BusinessException.FAIL_INVALID_PARAMETER);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(
            {HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class, HttpRequestMethodNotSupportedException.class, HttpClientErrorException.class})
    public ResponseEntity<ApiResult> httpMediaTypeNotAcceptableException(final Throwable e) {
        log.debug(ExceptionUtils.getStackTrace(e));
        return Result.error(BusinessException.FAIL_INVALID_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MultipartException.class})
    public ResponseEntity<ApiResult> handleMultipartException(final MultipartException e) {
        log.debug(ExceptionUtils.getStackTrace(e));
        final ResponseEntity<ApiResult> result;
//        if (ExceptionUtils.getMessage(e).contains(FileUploadBase.SizeLimitExceededException.class.getSimpleName())) {
//            result = new Response(ExceptionCode.FAIL_FILE_SIZE).getJsonObject();
//        } else {
        result = Result.error();
//        }
        return result;
    }
}