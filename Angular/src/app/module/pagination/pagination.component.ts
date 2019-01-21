import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PageService } from 'src/app/common/page.service';

@Component({
  selector: 'pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  pager: any = {}
  totalItems: number
  @Input() currentPage: number
  @Input() set setTotalItems(totalItems) {
    if (totalItems) {
      this.totalItems = totalItems;
    }
  }
  @Output() changePage = new EventEmitter()

  constructor(private pagingService: PageService) { }

  ngOnInit() {
  }

  ngOnChanges() {
    this.currentPage = this.pager.currentPage;
    this.setPage(this.currentPage, false);
  }

  /**
   * Set page
   * 
   * @since 29/12/2018
   * @param page 
   * @param flag 
   */
  setPage(page: number, flag: boolean) {
    if (page < 0 || page > this.pager.totalPages) {
      if (page - 1 == this.pager.totalPages) {
        page = this.pager.totalPages - 1;
      } else if (page - 2 == this.pager.totalPages) {
        page = this.pager.totalPages;
      }
    }
    if (flag) {
      if (page == 7) {
        page = 6;
      } else if (page == 8) {
        page = 6;
      }
    }

    this.pager = this.pagingService.getPage(this.totalItems, page);
    this.changePage.emit(this.pager);
  }

}