package tech.stepniewski;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Example2 {

    interface Result {}
    interface Results {
        record PaymentOk(String resultCode, String successPaymentId) implements Result {}
        record ClientHasNoFund(String resultCode, LocalDateTime nextTryAfter) implements Result {}
        record SingleTransactionLimitExceeded(String resultCode, BigInteger singleTransactionLimit) implements Result {}
        record DailyTransactionsLimitExceeded(String resultCode, BigInteger dailySumLimit) implements Result {}
        record CardBlocked(String resultCode, String blockedCardId) implements Result {}
    }

    public void example2() {
        // success execution - payment made correctly
        new Results.PaymentOk("PAYMENT_OK", "vanoh5ailuChay6p");
        // client has no fund on the card,
        new Results.ClientHasNoFund("CLIENT_HAS_NO_FUND", LocalDateTime.of(2021, 11, 30, 10, 0, 0));
        // payment exceeded single transaction maximum amount limit,
        new Results.SingleTransactionLimitExceeded("SINGLE_TRANSACTION_LIMIT_EXCEEDED", BigInteger.valueOf(20000));
        // payment exceeded daily transactions amounts sum limit,
        new Results.DailyTransactionsLimitExceeded("DAILY_TRANSACTIONS_LIMIT_EXCEEDED", BigInteger.valueOf(50000));
        // card has been blocked.
        new Results.CardBlocked("CARD_BLOCKED", "eoZi5chu");
    }

    public class PatternMatchingExample {

        public Message messageFor(Result result) {
            return switch (result) {
                case Results.PaymentOk v                      -> paymentOkMessage(v);
                case Results.ClientHasNoFund v                -> clientHasNoFund(v);
                case Results.SingleTransactionLimitExceeded v -> singleTransactionLimitExceeded(v);
                case Results.DailyTransactionsLimitExceeded v -> dailyTransactionsLimitExceeded(v);
                case Results.CardBlocked v                    -> cardBlocked(v);
                default                                       -> defaultMessage();
            };
        }
    }

    public class InstanceOfExample {
        public Message messageFor(Result result) {
            if(result instanceof Results.PaymentOk) {
                return paymentOkMessage((Results.PaymentOk) result);
            }
            else if(result instanceof Results.ClientHasNoFund) {
                return clientHasNoFund((Results.ClientHasNoFund) result);
            }
            else if(result instanceof Results.SingleTransactionLimitExceeded) {
                return singleTransactionLimitExceeded((Results.SingleTransactionLimitExceeded) result);
            }
            else if(result instanceof Results.DailyTransactionsLimitExceeded) {
                return dailyTransactionsLimitExceeded((Results.DailyTransactionsLimitExceeded) result);
            }
            else if(result instanceof Results.CardBlocked) {
                return cardBlocked((Results.CardBlocked) result);
            }
            else {
                return defaultMessage();
            }
        }
    }

    record Message(String messageId) {};

    public Message paymentOkMessage(Results.PaymentOk v) {
        return new Message("1");
    }

    public Message clientHasNoFund(Results.ClientHasNoFund v) {
        return new Message("2");
    }

    public Message singleTransactionLimitExceeded(Results.SingleTransactionLimitExceeded v) {
        return new Message("3");
    }

    public Message dailyTransactionsLimitExceeded(Results.DailyTransactionsLimitExceeded v) {
        return new Message("4");
    }

    public Message cardBlocked(Results.CardBlocked v) {
        return new Message("5");
    }

    public Message defaultMessage() {
        return new Message("6");
    }
}
