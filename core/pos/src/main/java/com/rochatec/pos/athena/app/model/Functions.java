package com.rochatec.pos.athena.app.model;

/**
 * Created by epr on 07/07/15.
 */
public enum Functions {

    READING_X(100),
    OPEN_DAY(101),
    ENTRY_OPERATOR(102),
    CONSULT_PRICE(103),
    REDUCTION_Z(109),
    LIST_FUNCTION(999);


    Functions(Integer code) {
        this.code = code;
    }

    private Integer code;

    public static Functions getByCode(Integer value){
        for (Functions functions : values()){
            if (functions.code.equals(value)){
                return functions;
            }
        }
        return null;
    }
}
