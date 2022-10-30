//package jpabook.jpashop.service;
//
//import jpabook.response.CommonResponse;
//import jpabook.response.ListResponse;
//import jpabook.response.SingleResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResponseService {
//    public<T> SingleResponse<T> getSingleResponse(T data){
//        SingleResponse singleResponse = new SingleResponse();
//        singleResponse.setData(data);
//        setSuccessResponse(singleResponse);
//        return singleResponse;
//    }
//    public<T>ListResponse<T> getListResponse(List<T> dataList){
//        ListResponse listResponse = new ListResponse();
//        listResponse.setDataList(dataList);
//        setSuccessResponse(listResponse);
//        return listResponse;
//    }
//    private void setSuccessResponse(CommonResponse response) {
//        response.setCode(0);
//        response.setSuccess(true);
//        response.setMessage("SUCCESS");
//    }
//}
