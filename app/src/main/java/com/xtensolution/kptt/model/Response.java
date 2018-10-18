/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xtensolution.kptt.model;

import com.xtensolution.core.utils.StringUtils;

/**
 * Created by MIT on 22-Jul-17.
 */

public class Response<T> {
    private boolean status;
//    private boolean success;
    private String success;
    private String message;
    private T result;

    public boolean isStatus() {
        return status;
    }

   /* public boolean isSuccess() {
        return success;
    }*/

    public String getMessage() {
        return message;
    }

    public T getData() {
        return result;
    }

    @Override
    public String toString() {
        return StringUtils.toJson(getClass(), this);
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
