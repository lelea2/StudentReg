package com.dao;

import java.util.ArrayList;
import com.entity.Major;

/**
 * Major object interface
 */
public interface MajorDAO {

    /**
     * Get all majors available
     * @return ArrayList of Major object
     */
    public ArrayList<Major> getMajors();
}
