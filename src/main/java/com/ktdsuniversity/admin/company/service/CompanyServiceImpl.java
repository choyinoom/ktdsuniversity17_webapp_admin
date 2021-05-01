package com.ktdsuniversity.admin.company.service;

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

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.company.dao.CompanyDAO;
import com.ktdsuniversity.admin.company.vo.CompanyVO;
import com.ktdsuniversity.admin.course.dao.CourseDAO;

import oracle.net.aso.n;

@Service("companyService")
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;

	// 전체 회사 출력 메소드
	@Override
	public List listCompanies() throws DataAccessException {
		List companiesList = null;
		companiesList = companyDAO.selectAllCompanyList();
		return companiesList;
	}

	// 회사 검색 메소드
	@Override
	public List listBySearchCompanies(String searchType, String searchText) throws DataAccessException {
		List companiesBySearchList = null;
		companiesBySearchList = companyDAO.selectBySearchCompanyList(searchType, searchText);
		return companiesBySearchList;
	}

	// 보여질 회사 개수 기준 나누는 메소드
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return companyDAO.listCriteria(criteria);
	}

	// 보여질 회사 개수 검색에 의해 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List companiesCriteriaBySearch = null;
		companiesCriteriaBySearch = companyDAO.selectCriteriaBySearch(criteria);
		return companiesCriteriaBySearch;
	}

	// 협력회사 출력 메소드
	@Override
	public List listPartners() throws DataAccessException {
		List partnersList = null;
		partnersList = companyDAO.selectAllPartnersList();
		return partnersList;
	}

	// 협력회사 검색 메소드
	@Override
	public List listBySearchPartners(String searchType, String searchText) throws DataAccessException {
		List partnersBySearchList = null;
		partnersBySearchList = companyDAO.selectBySearchPartnerList(searchType, searchText);
		return partnersBySearchList;
	}

	// 기준 나누는 메소드 (협력회사)
	@Override
	public List partnerListCriteria(Criteria criteria) throws DataAccessException {
		return companyDAO.partnerListCriteria(criteria);
	}

	// 검색에 의해 나눠지는 메소드 (협력회사)
	@Override
	public List partnerListCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List partnersCrteriaBySearch = null;
		partnersCrteriaBySearch = companyDAO.selectCriteriaBySearchToPartner(criteria);
		return partnersCrteriaBySearch;
	}

	// 회사 추가 메소드
	@Override
	public int addCompany(CompanyVO company) throws DataAccessException {
		return companyDAO.insertCompany(company);
	}

	// 회사명 선택 메소드
	@Override
	public CompanyVO selectCompany(String id) throws DataAccessException {
		return companyDAO.selectCompany(id);
	}

	// 회사 수정 메소드
	@Override
	public int modCompany(CompanyVO company) throws DataAccessException {
		return companyDAO.modCompany(company);
	}

	// 회사 삭제 메소드
	@Override
	public int removeCompany(String id) throws DataAccessException {
		return companyDAO.deleteCompany(id);
	}

	// 팝업창에서 검색 메소드
	@Override
	public List searchFromPopUp(String searchType, String searchText) throws DataAccessException {
		List companiesBySearchList = null;
		companiesBySearchList = companyDAO.selectBySearchFromPopUp(searchType, searchText);
		return companiesBySearchList;
	}

	// 팝업창에서 검색에 의해 나눠지는 메서드
	@Override
	public List listCriteriaBySearchFromPopUp(Criteria criteria) throws DataAccessException {
		List companiesCriteriaBySearch = null;
		companiesCriteriaBySearch = companyDAO.selectCriteriaBySearchFromPopUp(criteria);
		return companiesCriteriaBySearch;
	}

	@Override
	public int numberOfCompanies() throws DataAccessException {
		return companyDAO.selectNumberOfCompanies();
	}
	
	// 전체회사 엑셀 다운로드
	@Override
	public void companyExcelDownload(HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List <CompanyVO> companiesList = companyDAO.selectAllCompanyList();
		
		try {
			//Excel Down 시작
            Workbook workbook = new HSSFWorkbook();
            //시트생성
			Sheet sheet = workbook.createSheet("list_excel");
			
			//행, 열, 열번호
            Row row = null;
            Cell cell = null;
            int rowNo = 0;
            
            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();
    
            // 가는 경계선을 가집니다.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);
    
            // 배경색은 노란색입니다.
            headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
            // 데이터는 가운데 정렬합니다.
            headStyle.setAlignment(HorizontalAlignment.CENTER);
    
            // 데이터용 경계 스타일 테두리만 지정
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

            // 헤더 생성
            row = sheet.createRow(rowNo++);
    
            cell = row.createCell(0);
            cell.setCellStyle(headStyle);
            cell.setCellValue("상태");
    
            cell = row.createCell(1);
            cell.setCellStyle(headStyle);
            cell.setCellValue("회사명");
    
            cell = row.createCell(2);
            cell.setCellStyle(headStyle);
            cell.setCellValue("담당자");

            cell = row.createCell(3);
            cell.setCellStyle(headStyle);
            cell.setCellValue("전화번호");
    
            cell = row.createCell(4);
            cell.setCellStyle(headStyle);
            cell.setCellValue("사업자 등록번호");
    
            cell = row.createCell(5);
            cell.setCellStyle(headStyle);
            cell.setCellValue("등록일");
            
            // 데이터 부분 생성
            for(CompanyVO excelData : companiesList) {
                
                row = sheet.createRow(rowNo++);
                
                cell = row.createCell(0);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getContractStat());
                
                cell = row.createCell(1);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getname());
                
                cell = row.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getContractName());
                
                cell = row.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getManagerPhone());
                
                cell = row.createCell(4);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getid());
                
                cell = row.createCell(5);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getRegDate());
               
            }

            // 컨텐츠 타입과 파일명 지정
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=company_list.xls");

            // 엑셀 출력
            workbook.write(response.getOutputStream());
            workbook.close();
		} catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	@Override
	public void partnersExcelDownload(HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		List <CompanyVO> partnersList = companyDAO.selectAllPartnersList();
		
		try {
			//Excel Down 시작
            Workbook workbook = new HSSFWorkbook();
            //시트생성
			Sheet sheet = workbook.createSheet("list_excel");
			
			//행, 열, 열번호
            Row row = null;
            Cell cell = null;
            int rowNo = 0;
            
            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();
    
            // 가는 경계선을 가집니다.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);
    
            // 배경색은 노란색입니다.
            headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
            // 데이터는 가운데 정렬합니다.
            headStyle.setAlignment(HorizontalAlignment.CENTER);
    
            // 데이터용 경계 스타일 테두리만 지정
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

            // 헤더 생성
            row = sheet.createRow(rowNo++);
    
            cell = row.createCell(0);
            cell.setCellStyle(headStyle);
            cell.setCellValue("회사명");
    
            cell = row.createCell(1);
            cell.setCellStyle(headStyle);
            cell.setCellValue("협약 상태");
    
            cell = row.createCell(2);
            cell.setCellStyle(headStyle);
            cell.setCellValue("담당자");

            cell = row.createCell(3);
            cell.setCellStyle(headStyle);
            cell.setCellValue("전화번호");
    
            cell = row.createCell(4);
            cell.setCellStyle(headStyle);
            cell.setCellValue("사업자 등록번호");
    
            cell = row.createCell(5);
            cell.setCellStyle(headStyle);
            cell.setCellValue("등록일");
            
            // 데이터 부분 생성
            for(CompanyVO excelData : partnersList) {
                
                row = sheet.createRow(rowNo++);
                
                cell = row.createCell(0);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getname());
                
                cell = row.createCell(1);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getContractType());
                     
                cell = row.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getContractName());
                
                cell = row.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getManagerPhone());
                
                cell = row.createCell(4);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getid());
                
                cell = row.createCell(5);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(excelData.getRegDate());
               
            }

            // 컨텐츠 타입과 파일명 지정
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=partners_list.xls");

            // 엑셀 출력
            workbook.write(response.getOutputStream());
            workbook.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// 사업자 등록번호 중복 체크
	@Override
	public int idCheck(CompanyVO vo) throws DataAccessException {
		int result = companyDAO.idCheck(vo);
		return result;
	}
}
