package tech.stepniewski;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Example1 {

    record CardPaymentResult(String resultCode,
                             String successPaymentId,
                             LocalDateTime nextTryAfter,
                             BigInteger singleTransactionLimit,
                             BigInteger dailySumLimit,
                             String blockedCardId) {}

    public void example1() {
        // success execution - payment made correctly
        new CardPaymentResult("PAYMENT_OK", "vanoh5ailuChay6p", null, null, null, null);
        // client has no fund on the card,
        new CardPaymentResult("CLIENT_HAS_NO_FUND", null, LocalDateTime.of(2021, 11, 30, 10, 0, 0), null, null, null);
        // payment exceeded single transaction maximum amount limit,
        new CardPaymentResult("SINGLE_TRANSACTION_LIMIT_EXCEEDED", null, null, BigInteger.valueOf(20000), null, null);
        // payment exceeded daily transactions amounts sum limit,
        new CardPaymentResult("DAILY_TRANSACTIONS_LIMIT_EXCEEDED", null, null,null, BigInteger.valueOf(50000), null);
        // card has been blocked.
        new CardPaymentResult("CARD_BLOCKED", null, null,null, null, "eoZi5chu");
    }
}
