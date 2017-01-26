package com.staff.personal.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * Created by mtustanovskyy on 1/24/17.
 */
@Slf4j
public class PatchUtil {

    private static PatchUtil patchUtil;

    private PatchUtil(){

    }

    public static PatchUtil getInstance(){
        if(patchUtil == null){
            patchUtil = new PatchUtil();
        }
        return patchUtil;
    }

    public void change(Set<Map.Entry<String, JsonElement>> setFromDTO, JsonObject wholeObject) {
        for (Map.Entry<String, JsonElement> entry : setFromDTO
                ) {
            JsonElement concreteEntity = entry.getValue();
            log.info(concreteEntity.toString() + " :el");
            log.info(wholeObject.toString() + " :obj");
            if (concreteEntity.isJsonPrimitive()) {
                wholeObject.add(entry.getKey(), entry.getValue());
            } else if (concreteEntity.isJsonArray()) {
                JsonArray jsonArray = concreteEntity.getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    log.info(jsonArray.get(i).toString());
                    if (jsonArray.get(i).getAsJsonObject().get("id") != null) {
                        log.info("not null");
                        for (Map.Entry<String, JsonElement> changeFromDTO : jsonArray.get(i).getAsJsonObject().entrySet()) {
                            for(int j = 0; j < wholeObject.get(entry.getKey()).getAsJsonArray().size(); j++) {
                                if(wholeObject.get(entry.getKey()).getAsJsonArray().get(j).getAsJsonObject().get("id").getAsInt() == jsonArray.get(i).getAsJsonObject().get("id").getAsInt()){
                                    log.info("whole: " + wholeObject);
                                    wholeObject.get(entry.getKey()).getAsJsonArray().get(j).getAsJsonObject().remove(changeFromDTO.getKey());

                                    wholeObject.get(entry.getKey()).getAsJsonArray().get(j).getAsJsonObject().add(changeFromDTO.getKey(), changeFromDTO.getValue());
                                    log.info("whole: " + wholeObject);
                                }
                            }

                        }
                        // this.change(jsonArray.get(i).getAsJsonObject().entrySet(), wholeObject.get(entry.getKey()).getAsJsonArray().get(i).getAsJsonObject());
                    } else {
                        log.info("1: " + wholeObject.get(entry.getKey()).getAsJsonArray());
                        wholeObject.get(entry.getKey()).getAsJsonArray().add(jsonArray.get(i).getAsJsonObject());
                        log.info("2: " + wholeObject.get(entry.getKey()).getAsJsonArray());
                    }

                    // this.change(jsonArray.get(i).getAsJsonObject().entrySet(), wholeObject.get(entry.getKey()).getAsJsonArray().get(i).getAsJsonObject());
//
//                    this.change(jsonArray.get(i).getAsJsonObject().entrySet(), wholeObject.get(entry.getKey()).getAsJsonArray().get(i).getAsJsonObject());

                }
            } else if (concreteEntity.isJsonObject()) {
                if (wholeObject.get(entry.getKey()) == null) {
                    wholeObject.add(entry.getKey(), new JsonObject());
                }
                this.change(concreteEntity.getAsJsonObject().entrySet(), wholeObject.get(entry.getKey()).getAsJsonObject());
            }


        }
    }


}
