package edu.neu.numad22sp_bdd_project.register;

import androidx.annotation.Nullable;

/**
 * Data validation state of the register form.
 */
class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer DOBError;
    @Nullable
    private Integer genderError;
    @Nullable
    private Integer countryError;
    private boolean isDataValid;

    RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError,
                      @Nullable Integer nameError, @Nullable Integer DOBError,
                      @Nullable Integer genderError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.nameError = nameError;
        this.DOBError = DOBError;
        this.genderError = genderError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.nameError = null;
        this.DOBError = null;
        this.genderError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    @Nullable
    Integer getDOBError() {
        return DOBError;
    }

    @Nullable
    Integer getGenderError() {
        return genderError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}

