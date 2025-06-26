/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package Service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author AMD
 */
@WebService(serviceName = "WebServiceSuma")
public class WebServiceSuma {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "suma")
    public int suma(@WebParam(name = "numero1") int numero1, @WebParam(name = "numero2") int numero2) {
        return numero1 + numero2;
    }
}
