package sk.ahitka.vetshop.domain.specification;

public enum SearchOperation {
    EQUALITY,
    NEGATION,
    GREATER_THAN,
    LESS_THAN,
    LIKE,
    STARTS_WITH,
    ENDS_WITH,
    CONTAINS;

    public static final String[] OPERATION_SET = { ":", "!", ">", "<", "~" };

    public static final String OR = "'";

    public static final String ZERO_OR_MORE = "*";

    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            default:
                return null;
        }
    }
}