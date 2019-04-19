/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Dorra
 */

public class SmsService {
    public static final String ACCOUNT_SID = "ACed820621f7c9b919ec960ad9118fcd5c";
    public static final String AUTH_TOKEN = "2627619814422b8411d32a357b82ffe7";
    String x = "6qC3RUQVw3ihWHS6Uf56ZTtj0UBP4XOGAMpFWHv5";

    public void sendSms(String x) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message msg = Message.creator(new PhoneNumber("+21650927486"), new PhoneNumber("+12016279424"), x).create();
    }
}
