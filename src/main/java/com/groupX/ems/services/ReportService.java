package com.groupX.ems.services;

import com.groupX.ems.view_models.ReportViewModel;

import java.util.List;

public interface ReportService {
    List<ReportViewModel> generateReports();
}

