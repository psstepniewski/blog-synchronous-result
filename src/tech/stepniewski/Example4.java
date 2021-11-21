package tech.stepniewski;

import io.vavr.control.Either;

public class Example4 {

    record PaymentOk(String resultCode, String successPaymentId) implements Example2.Result {}
    record PaymentFail(String resultCode, String failReason) implements Example2.Result {}

    public Either<PaymentFail, PaymentOk> example4() {
        //omitted code
        return Either.right(new PaymentOk("PAYMENT_OK", "vanoh5ailuChay6p"));
    }
}
