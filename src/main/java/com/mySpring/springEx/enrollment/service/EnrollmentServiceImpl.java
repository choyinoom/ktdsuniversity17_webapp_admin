package com.mySpring.springEx.enrollment.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.enrollment.dao.EnrollmentDAO;
import com.mySpring.springEx.enrollment.vo.EnrollmentVO;

@Service("enrollmentService")
@Transactional(propagation = Propagation.REQUIRED)
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentDAO enrollmentDAO;
	
	@Override
	public void excelDownload(HttpServletResponse response) throws Exception {
		
		List <EnrollmentVO> enrollmentList = enrollmentDAO.excelEnrollmentList();
		
		try {
			//Excel Down ����
            Workbook workbook = new HSSFWorkbook();
            //��Ʈ����
			Sheet sheet = workbook.createSheet("list_excel");
			
			//��, ��, ����ȣ
            Row row = null;
            Cell cell = null;
            int rowNo = 0;
            
            // ���̺� ����� ��Ÿ��
            CellStyle headStyle = workbook.createCellStyle();
    
            // ���� ��輱�� �����ϴ�.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);
    
            // ������ ������Դϴ�.
            headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
            // �����ʹ� ��� �����մϴ�.
            headStyle.setAlignment(HorizontalAlignment.CENTER);
    
            // �����Ϳ� ��� ��Ÿ�� �׵θ��� ����
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

            // ��� ����
            row = sheet.createRow(rowNo++);
    
            cell = row.createCell(0);
            cell.setCellStyle(headStyle);
            cell.setCellValue("��ȣ");
    
            cell = row.createCell(1);
            cell.setCellStyle(headStyle);
            cell.setCellValue("���Ǹ�");
    
            cell = row.createCell(2);
            cell.setCellStyle(headStyle);
            cell.setCellValue("���ǽ�����");

            cell = row.createCell(3);
            cell.setCellStyle(headStyle);
            cell.setCellValue("����������");
    
            cell = row.createCell(4);
            cell.setCellStyle(headStyle);
            cell.setCellValue("�̸�");
    
            cell = row.createCell(5);
            cell.setCellStyle(headStyle);
            cell.setCellValue("ȸ��");
            
            cell = row.createCell(6);
            cell.setCellStyle(headStyle);
            cell.setCellValue("����");
    
            cell = row.createCell(7);
            cell.setCellStyle(headStyle);
            cell.setCellValue("�������");
    
            cell = row.createCell(8);
            cell.setCellStyle(headStyle);
            cell.setCellValue("����");
            
            cell = row.createCell(9);
            cell.setCellStyle(headStyle);
            cell.setCellValue("�����");
			
            // ������ �κ� ����
            for(EnrollmentVO excelData : enrollmentList) {
                
                row = sheet.createRow(rowNo++);
                
                cell = row.createCell(0);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getId());
                
                cell = row.createCell(1);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getSyllabusVO().getName());
                
                cell = row.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getCourseVO().getStartDate());
                
                cell = row.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getCourseVO().getEndDate());
                
                cell = row.createCell(4);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getMemberVO().getName());
                
                cell = row.createCell(5);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getMemberVO().getCompanyName());
                
                cell = row.createCell(6);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getCompanyVO().getContractStat());
                
                cell = row.createCell(7);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getCompanyVO().getContractStat());
                
                cell = row.createCell(8);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getStat());
                
                cell = row.createCell(9);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getJoinDate());
            }

            
            
            // ������ Ÿ�԰� ���ϸ� ����
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=enrollment_list.xls");

            // ���� ���
            workbook.write(response.getOutputStream());
            workbook.close();
		} catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	//���� ������ �޼��� 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return enrollmentDAO.listCriteria(criteria);
	}
	
	//�˻��� ���� �������� �޼���
	@Override
	public List listBySearchEnrollments(String searchType, String searchText) throws DataAccessException {
		List enrollmentsBySearchList = null;
		enrollmentsBySearchList = enrollmentDAO.selectBySearchEnrollmentList(searchType, searchText);
		return enrollmentsBySearchList;
	}
	
	//�˻��� ���ؿ� ���� ����Ʈ �������� �޼���
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List enrollmentsCriteriaBySearch = null;
		enrollmentsCriteriaBySearch = enrollmentDAO.selectCriteriaBySearch(criteria);
		return enrollmentsCriteriaBySearch;
	}
	
	//(��ϰ���) ������û �Ǿ��ִ��� üũ
	@Override
	public int checkEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.checkEnrollment(enrollment);
	}

	
	//��� ������ ����Ʈ ���
	@Override
	public List listSylCrs() throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectSylCrsList();
		return sylCrsList;
	}
	
	//��� ������ ����Ʈ ��� (������)
	@Override
	public List exceptListSylCrs(String id) throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectExceptList(id);
		return sylCrsList;
	}
	
	//������û���� ����Ʈ�� �̵�
	@Override
	public List listEnrollments() throws DataAccessException {
		List enrollmentsList = null;
		enrollmentsList = enrollmentDAO.selectAllEnrollmentList();
		return enrollmentsList;
	}
	
	//������û ���
	@Override
	public int addEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.insertEnrollment(enrollment);
	}
	
	//�� �������� �������� �̵�
	@Override
	public EnrollmentVO selectEnrollment(int id) throws DataAccessException {
		return enrollmentDAO.selectEnrollment(id);
	}
	
	// �������� ���� ����
	@Override
	public int modEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		
		enrollmentDAO.modEnrollmentCompany(enrollment);
		return enrollmentDAO.modEnrollmentStat(enrollment);
	}
	
	// ���� '����' �� ����
	@Override 
	public int updateDeleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateDeleteEnrollments(id);
	}
	
	// ���� '����' ���� ����
	@Override 
	public int updateApproveEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateApproveEnrollments(id);
	}
		
	// ���� '����' �� ����
	@Override 
	public int updateCompleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateCompleteEnrollments(id);
	}
	
}
