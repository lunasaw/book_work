<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专业ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入专业ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入学生ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否领取" prop="stockStatus">
        <el-select v-model="queryParams.stockStatus" placeholder="请选择是否领取" clearable>
          <el-option
            v-for="dict in dict.type.tb_stock_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="书籍列表" prop="books">
        <el-input
          v-model="queryParams.books"
          placeholder="请输入书籍列表"
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
          v-hasPermi="['book:sotck:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['book:sotck:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['book:sotck:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['book:sotck:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sotckList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="领取记录" align="center" prop="id" />
      <el-table-column label="专业名称" align="center" prop="deptName" />
      <el-table-column label="学生姓名" align="center" prop="userName" />
      <el-table-column label="是否领取" align="center" prop="stockStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tb_stock_status" :value="scope.row.stockStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="书籍列表" align="center" prop="books" >
      <template slot-scope="scope">
        <el-select multiple v-model="scope.row.bookIds" laceholder="请输入课程名">
          <el-option
            v-for="item in scope.row.bookList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['book:sotck:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['book:sotck:remove']"
          >删除</el-button>
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

    <!-- 添加或修改书籍领取列对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专业名" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择专业"/>
        </el-form-item>

        <el-form-item label="班级负责人" prop="userId" label-width="100px">
          <el-select v-model="form.userId" placeholder="请选择班级负责">
            <el-option
              v-for="item in stuList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否领取">
          <el-radio-group v-model="form.stockStatus">
            <el-radio
              v-for="dict in dict.type.tb_stock_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="书籍列表" prop="books">
          <el-select v-model="form.bookIds" multiple remote filterable size="medium"
                     default-first-option placeholder="请选择书籍列表"
          >
            <el-option
              v-for="item in bookList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              :disabled="item.status === 1"
            ></el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listSotck, getSotck, delSotck, addSotck, updateSotck } from "@/api/book/sotck";
import {
  listTeachclass,
  getTeachclass,
  delTeachclass,
  addTeachclass,
  updateTeachclass,
  listTeachClassStuList
} from '@/api/book/teachclass'
import { treeselect } from '@/api/system/dept'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: "Sotck",
  dicts: ['tb_stock_status'],
  components: { Treeselect },
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
      // 书籍领取列表格数据
      sotckList: [],
      // 班级学生用户
      stuList: [],
      // 书籍列表
      bookList: [],
      // 部门树选项
      deptOptions: undefined,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        userId: null,
        stockStatus: null,
        books: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "专业ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "学生ID不能为空", trigger: "blur" }
        ],
        stockStatus: [
          { required: true, message: "是否领取不能为空", trigger: "blur" }
        ],
        books: [
          { required: true, message: "书籍列表不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getTreeselect(this.$store.state.user.classId)
  },
  methods: {
    /** 查询书籍领取列列表 */
    getList() {
      this.loading = true;
      listSotck(this.queryParams).then(response => {
        this.sotckList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        deptId: null,
        userId: null,
        stockStatus: "0",
        books: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 查询部门下拉树结构 */
    getTreeselect(classId) {
      treeselect().then(response => {
        this.deptOptions = response.data
      })
      if (null != classId) {
        listTeachClassStuList(classId).then(response => {
          this.stuList = response
          this.form.stuList = this.stuList
          console.log(response)
        })
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加书籍领取列";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      this.getTreeselect(row.sysUser.classId)
      getSotck(id).then(response => {
        this.form = response.data;
        this.bookList = response.data.bookList
        this.open = true;
        this.title = "修改书籍领取列";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSotck(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSotck(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除书籍领取列编号为"' + ids + '"的数据项？').then(function() {
        return delSotck(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('book/sotck/export', {
        ...this.queryParams
      }, `sotck_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
