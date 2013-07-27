/*
 * Copyright (C) 2012 The Regents of The University California.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shark.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.serde.Constants;

/**
 * List of data types defined in Shark APIs.
 */
public class DataTypes {

  // This list of types are defined in a Java class for better interoperability with Shark's
  // Java APIs.
  public static final DataType BOOLEAN = new DataType("boolean", Constants.BOOLEAN_TYPE_NAME, true);

  public static final DataType TINYINT = new DataType("tinyint", Constants.TINYINT_TYPE_NAME, true);

  public static final DataType SMALLINT =
      new DataType("smallint", Constants.SMALLINT_TYPE_NAME, true);

  public static final DataType INT = new DataType("int", Constants.INT_TYPE_NAME, true);

  public static final DataType BIGINT = new DataType("bigint", Constants.BIGINT_TYPE_NAME, true);

  public static final DataType FLOAT = new DataType("float", Constants.FLOAT_TYPE_NAME, true);

  public static final DataType DOUBLE = new DataType("double", Constants.DOUBLE_TYPE_NAME, true);

  public static final DataType STRING = new DataType("string", Constants.STRING_TYPE_NAME, true);

  public static final DataType TIMESTAMP =
      new DataType("timestamp", Constants.TIMESTAMP_TYPE_NAME, true);

  public static final DataType DATE = new DataType("date", Constants.DATE_TYPE_NAME, true);

  public static final DataType BINARY = new DataType("binary", Constants.BINARY_TYPE_NAME, true);

  private static DataType[] types = {
      BOOLEAN, TINYINT, SMALLINT, INT, BIGINT, FLOAT, DOUBLE, STRING, TIMESTAMP, DATE, BINARY
  };

  private static Map<String, DataType> hiveTypes = new HashMap<String, DataType>();
  static {
    for (DataType type: types) {
      hiveTypes.put(type.hiveName, type);
    }
  }

  static class DataType implements Serializable {

    public final String name;
    public final String hiveName;
    public final boolean isPrimitive;

    private DataType(String name, String hiveName, boolean isPrimitive) {
      this.name = name;
      this.hiveName = hiveName;
      this.isPrimitive = isPrimitive;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  static class UnknownDataTypeException extends Exception {

  }

  /**
   * Return the DataType based on the string representation of a Hive data type.
   * @throws UnknownDataTypeException
   */
  public static DataType fromHiveType(String hiveType) throws UnknownDataTypeException {
    DataType type = hiveTypes.get(hiveType);
    if (null == type) {
      throw new UnknownDataTypeException();
    }
    return type;
  }
}
