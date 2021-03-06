<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入课程ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课时" prop="hour">
        <el-input
          v-model="queryParams.hour"
          placeholder="请输入课时"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学年" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入学年"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['book:course:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['book:course:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['book:course:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['book:course:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="课程ID" align="center" prop="id"/>
      <el-table-column label="课程名称" align="center" prop="name"/>
      <el-table-column label="课时" align="center" prop="hour"/>
      <el-table-column label="学期" align="center" prop="semester"/>
      <el-table-column label="学年" align="center" prop="year"/>
      <el-table-column label="书籍列表" align="center" prop="books" align-="center"  width="200px">
        <template slot-scope="scope">
          <el-select multiple v-model="scope.row.bookIds" laceholder="请输入课程名"
          >
            <el-option
              v-for="item in scope.row.bookList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['book:course:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['book:course:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程列对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="name" v-if="checkPermi(['book:course:add'])">
          <el-input v-model="form.name" placeholder="请输入课程名称"/>
        </el-form-item>
        <el-form-item label="课时" prop="hour" v-if="checkPermi(['book:course:add'])">
          <el-input v-model="form.hour" placeholder="请输入课时" />
        </el-form-item>
        <el-form-item label="学期" prop="semester" v-if="checkPermi(['book:course:add'])">
          <el-input v-model="form.semester" placeholder="请输入学期"/>
        </el-form-item>
        <el-form-item label="学年" prop="year" v-if="checkPermi(['book:course:add'])">
          <el-input v-model="form.year" placeholder="请输入学年"/>
        </el-form-item>
        <el-form-item label="书籍列表" prop="books" v-if="checkPermi(['book:course:edit'])" >
          <el-select v-model="form.bookIds" multiple remote filterable size="medium"
                     default-first-option placeholder="请选择书籍列表"
          >
            <el-option
              v-for="item in bookList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              :disabled="item.status === 0"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from '@/api/book/course'
import { listWithChecked, listWork, getWork } from '@/api/book/work'
import { getAuthRole } from '@/api/system/user'
import { checkPermi, checkRole } from "@/utils/permission"; // 权限判断函数

export default {
  name: 'Course',
  checkPermi,
  dicts: ['tb_book_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程列表格数据
      courseList: [],
      // 书籍列表
      bookList: [],
      // 书籍列表
      bookIds: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        hour: null,
        semester: null,
        year: null,
        books: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: '课程名称不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    const courseId = this.$route.params && this.$route.params.courseId;
    console.log(courseId)
    if (courseId) {
      this.loading = true;
      this.queryParams.id = courseId;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows
        this.total = response.total
        this.loading = false
      })
    }
    this.getList()
  },
  methods: {
    checkPermi,
    /** 查询课程列列表 */
    getList() {
      this.loading = true
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        hour: null,
        semester: null,
        year: null,
        books: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加课程列'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getCourse(id).then(response => {  
        this.form = response.data

        this.bookIds = this.form.bookIds
        console.log(this.bookIds)
        listWithChecked().then(response => {
          this.bookList = response.rows
          console.log(
            this.bookList
          )
          this.open = true
        })
        this.title = '修改课程列'
      })

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        console.log(this.form.bookIds)
        if (valid) {
          if (this.form.id != null) {
            console.log(this.bookIds)
            this.form.books = this.form.bookIds.join(',')
            console.log(this.form.books)
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addCourse(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除课程列编号为"' + ids + '"的数据项？').then(function() {
        return delCourse(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('book/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
