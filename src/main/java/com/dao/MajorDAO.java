package com.dao;

import java.util.ArrayList;
import com.entity.Major;
import com.util.exception.DAOException;

/**
 * Major object interface
 */
public interface MajorDAO {

    /**
     * Get all majors available
     * @return ArrayList of Major object
     */
    public ArrayList<Major> getMajors() throws DAOException;
}
