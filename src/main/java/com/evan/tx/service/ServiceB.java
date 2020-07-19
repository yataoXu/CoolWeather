@Slf4j
@ControllerAdvice
public class ExceptionHandlerBean extends ResponseEntityExceptionHandler {

    /**
     * 数据找不到异常
     *
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex, WebRequest request) throws IOException {
        return getResponseEntity(ex, request, ReturnStatusCode.DataNotFoundException);
    }

    /**
     * 根据各种异常构建 ResponseEntity  服务于以上各种异常
     *
     * @param ex
     * @param request
     * @param specificException
     * @return
     */
    private ResponseEntity<Object> getResponseEntity(RuntimeException ex, WebRequest request, ReturnStatusCode specificException) {

        ReturnTemplate returnTemplate = new ReturnTemplate();
        returnTemplate.setStatusCode(specificException);
        returnTemplate.setErrorMsg(ex.getMessage());

        return handleExceptionInternal(ex, returnTemplate,
                new HttpHeaders(), HttpStatus.OK, request);
    }

}