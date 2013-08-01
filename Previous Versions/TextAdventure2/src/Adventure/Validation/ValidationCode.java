package Adventure.Validation;

/**
 * This enum is used by the Validator class to keep track of all the reasons it might say a name is invalid.
 */
public enum ValidationCode
{
    VALIDATION_PASSED,
    INVALID_NAME_LENGTH,
    INVALID_HOTKEY_LENGTH,
    NAME_DUPLICATION,
    HOTKEY_DUPLICATION,
    NAME_DUPLICATES_HOTKEY,
    HOTKEY_DUPLICATES_NAME,
    ALIAS_DUPLICATES_NAME,
    NAME_DUPLICATES_ALIAS,
    HOTKEY_DUPLICATES_ALIAS,
    INVALID_WHITEPACE,
}
