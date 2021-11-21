package tech.stepniewski;

public class Example3 {

    public enum Result {
        PAYMENT_OK, CLIENT_HAS_NO_FUND, SINGLE_TRANSACTION_LIMIT_EXCEEDED, DAILY_TRANSACTIONS_LIMIT_EXCEEDED, CARD_BLOCKED
    }

    public void example3() {
        // success execution - payment made correctly
        Result v1 = Result.PAYMENT_OK;
        // client has no fund on the card,
        Result v2 = Result.CLIENT_HAS_NO_FUND;
        // payment exceeded single transaction maximum amount limit,
        Result v3 = Result.SINGLE_TRANSACTION_LIMIT_EXCEEDED;
        // payment exceeded daily transactions amounts sum limit,
        Result v4 = Result.DAILY_TRANSACTIONS_LIMIT_EXCEEDED;
        // card has been blocked.
        Result v5 = Result.CARD_BLOCKED;
    }

    public class PatternMatchingExample {

        public Message messageFor(Result result) {
            return switch (result) {
                case PAYMENT_OK                        -> paymentOkMessage();
                case CLIENT_HAS_NO_FUND                -> clientHasNoFund();
                case SINGLE_TRANSACTION_LIMIT_EXCEEDED -> singleTransactionLimitExceeded();
                case DAILY_TRANSACTIONS_LIMIT_EXCEEDED -> dailyTransactionsLimitExceeded();
                case CARD_BLOCKED                      -> cardBlocked();
                default                                -> defaultMessage();
            };
        }

        record Message(String messageId) {};

        public Message paymentOkMessage() {
            return new Message("1");
        }

        public Message clientHasNoFund() {
            return new Message("2");
        }

        public Message singleTransactionLimitExceeded() {
            return new Message("3");
        }

        public Message dailyTransactionsLimitExceeded() {
            return new Message("4");
        }

        public Message cardBlocked() {
            return new Message("5");
        }

        public Message defaultMessage() {
            return new Message("6");
        }
    }
}
