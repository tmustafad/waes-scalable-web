package com.rsouza01.waesscalableweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum JsonContentsResult {
    EQUAL_CONTENTS,
    DIFFERENT_SIZES_CONTENTS,
    SAME_SIZES_BUT_DIFFERENT_CONTENTS
}
