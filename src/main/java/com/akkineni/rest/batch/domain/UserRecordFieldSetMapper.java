/**
 * 
 */
package com.akkineni.rest.batch.domain;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author va2839
 * 
 */
public class UserRecordFieldSetMapper implements FieldSetMapper<UserRecord> {

	@Override
	public UserRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		UserRecord ur = new UserRecord();
		ur.setUid(fieldSet.readString("USER_ID"));
		ur.setWorkgroup(fieldSet.readString("WORKGROUP"));
		return ur;
	}

}
