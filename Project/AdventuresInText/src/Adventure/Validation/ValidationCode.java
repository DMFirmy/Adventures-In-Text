package Adventure.Validation;

/**
 * This enum is used by the Validator class to keep track of all the reasons it might say a name is not valid.
 */
public enum ValidationCode
{
    ALIAS_DUPLICATES_NAME,
    HOTKEY_DUPLICATES_ALIAS,
    HOTKEY_DUPLICATES_NAME,
    HOTKEY_DUPLICATION,
    INVALID_HOTKEY_LENGTH,
    INVALID_NAME_LENGTH,
    INVALID_WHITEPACE,
    NAME_DUPLICATES_ALIAS,
    NAME_DUPLICATES_HOTKEY,
    NAME_DUPLICATION,
    VALIDATION_PASSED,
}
